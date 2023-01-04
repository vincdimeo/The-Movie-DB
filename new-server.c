
#include<stdio.h>
#include<string.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<unistd.h>

int main(int argc, char const *argv[]) {
  int socket_desc, client_sock, c, read_size;
	struct sockaddr_in server, client;
	char client_message[2000];

  //Create socket
	socket_desc = socket(AF_INET, SOCK_STREAM, 0);

  if (socket_desc == -1) {
    printf("Errore durante creazione socket");
  }

  //puts("Socket creata correttamente");

  //Prepare the sockaddr_in structure
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(8080);

  if (bind(socket_desc, (struct sockaddr *)&server, sizeof(server)) < 0) {
    //print the error message
		perror("Bind fallito");
		return 1;
  }

  //puts("Bind eseguito");

  //Listen
	listen(socket_desc , 3);

  puts("*** Benvenuto sul server TheMovieDB ***");

  //Accept and incoming connection
	puts("Server in ascolto...");
	c = sizeof(struct sockaddr_in);

	//accept connection from an incoming client
	client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c);

  if (client_sock < 0) {
    perror("Errore durante connessione con il client");
		return 1;
  }

  puts("Client connesso");

  //Receive a message from client
	while ((read_size = recv(client_sock , client_message , 2000 , 0)) > 0 ) {
    //Send the message back to client
		write(client_sock , "OK" , 2);
  }

  if (read_size == 0) {
		puts("Client disconnesso");
		fflush(stdout);
	}
	else if (read_size == -1)	{
		perror("recv failed");
	}

  return 0;
}
