#include <mysql/mysql.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/ip.h>
#include <netinet/in.h>
#include <unistd.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>
#include <pthread.h>

struct User {
  char username[100];
  char password[100];
  char accessibility[100];
};

MYSQL *start_connection(MYSQL *conn);
MYSQL_RES *send_query_insert(MYSQL *conn, MYSQL_RES *res, struct User user);
bool registration(struct User user, MYSQL *conn, MYSQL_RES *res);
void *connection_handler(void *);

int main(int argc, char const *argv[]) {
  int socket_desc, client_sock, c, *new_sock;
	struct sockaddr_in server , client;
	char client_message[100];

  //Create socket
	socket_desc = socket(AF_INET , SOCK_STREAM , 0);
	if (socket_desc == -1) {
		printf("Errore durante creazione della socket");
	}

  //Prepare the sockaddr_in structure
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(8080);

  //Bind
	if(bind(socket_desc, (struct sockaddr *)&server, sizeof(server)) < 0) {
		//print the error message
		perror("Bind fallito");
		return 1;
	}

  //Listen
	listen(socket_desc, 3);

  printf("\n*** BENVENUTO SUL SERVER DI TheMovieDB ***\n\n");

  //Accept and incoming connection
	printf("Server in ascolto su porta 8080...\n\n");
	c = sizeof(struct sockaddr_in);

	while ((client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c))) {
    printf("Connessione con il client stabilita\n\n");

    pthread_t sniffer_thread;
		new_sock = malloc(1);
		*new_sock = client_sock;

    if (pthread_create(&sniffer_thread, NULL, connection_handler, (void*) new_sock) < 0) {
      perror("Errore durante la creazione del thread\n\n");
			return 1;
    }

    //puts("Handler assegnato");
  }

  if (client_sock < 0) {
		perror("Connessione con il client fallita\n\n");
		return 1;
	}

  return 0;
}

MYSQL *start_connection(MYSQL *conn) {
  char *server = "localhost";
  char *user = "root";
  char *password = "Unina@2023"; /* set me first */
  char *database = "themoviedb";

  conn = mysql_init(NULL);

  /* Connect to database */
  if (!mysql_real_connect(conn, server, user, password, database, 0, NULL, 0)) {
    fprintf(stderr, "%s\n", mysql_error(conn));
    exit(1);
  }

  return conn;
}

MYSQL_RES *send_query_insert(MYSQL *conn, MYSQL_RES *res, struct User user) {
  char query_insert[1000];
  sprintf(query_insert, "INSERT INTO user(username, password, accessibility) VALUES ('%s', '%s', '%s')", user.username, user.password, user.accessibility);

  if (mysql_query(conn, query_insert)) {
    fprintf(stderr, "%s\n\n", mysql_error(conn));
    exit(1);
  }

  res = mysql_use_result(conn);

  return res;
}

bool registration(struct User user, MYSQL *conn, MYSQL_RES *res) {
  res = send_query_insert(conn, res, user);

  if (res != NULL) {
    return false;
  }
  else {
    return true;
  }
}

void *connection_handler(void *socket_desc) {
  //Get the socket descriptor
	int sock = *(int*)socket_desc;
	int read_size;
  char *message , client_message[2000];

  MYSQL *conn;
  MYSQL_RES *res;
  MYSQL_ROW row;

  conn = start_connection(conn);

  //Receive a message from client
	while ((read_size = recv(sock , client_message , 2000 , 0)) > 0 ) {
    char* token = strtok(client_message, ";");

    if (token != NULL) {
      if (strcmp(token, "Registrazione") == 0) {
        printf("Registrazione nuovo utente in corso...\n\n");
        struct User user;

        token = strtok(NULL, ";");
        strcpy(user.username, token);

        token = strtok(NULL, ";");
        strcpy(user.password, token);

        token = strtok(NULL, ";");
        strcpy(user.accessibility, token);

        if (registration(user, conn, res)) {
          printf("Registrazione avvenuta con successo\n\n");
        }
        else {
          printf("Errore durante registrazione\n\n");
        }
      }
    }

    //Send the message back to client
		//write(sock , client_message , strlen(client_message));
	}

  if (read_size == 0)	{
		puts("Client disconnesso\n\n");
		fflush(stdout);
	}
	else if (read_size == -1)	{
		perror("recv failed");
	}

	//Free the socket pointer
	free(socket_desc);

  return 0;
}
