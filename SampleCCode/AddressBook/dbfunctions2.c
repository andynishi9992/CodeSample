/*
Name: Andrew Nishimura 

file: dbfunctions2.c

class: ICS212

homwork: project 1

description: this file contains the definitions 
and the prototypes for each database function



*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "record.h"

int addRecord (struct record **,char [],char [],int, char []);

int printRecord (struct record *, char []);   

int modifyRecord (struct record *, char [],char [], char []);  

void printAllRecords(struct record *);

int deleteRecord(struct record **, char []);

int readfile(struct record **, char []);

void writefile(struct record *, char []); 



/*
function: addRecord

parameters: struct record **start , char uname[],char uaddr[], int yob, char utelno[]

descrption: it creates more data in heap and adds a record to linked list

return: 0 if add record failed
1 if addrecord worked

*/
int addRecord ( struct record **start , char uname[],char uaddr[], int yob, char utelno[])

{
	int work;
	struct record *recorditer;
	struct record *temp = (struct record*)malloc(sizeof(struct record ));
	work =0;
	strcpy(temp -> name,uname);
	strcpy(temp -> address,uaddr);
	temp -> yearofbirth = yob;
	strcpy(temp -> telno,utelno);
	temp -> next = NULL;
	if(*start == NULL)
	{
		*start = temp;
		work = 1;
	}
	else
	{
		recorditer = *start;
		while(recorditer -> next != NULL)
		{
			recorditer = recorditer -> next;
		}
		recorditer -> next = temp;
		work = 1;
	}
	return work;
}
/*
function: delete record

parameters: struct record **start, char uname[]
descrption: it deletes a record in the heap 

return: 0 if  deleterecord failed (aka no records or name doesnt match)
1 if delete record worked

*/
int deleteRecord( struct record **start, char uname[])
{
	int work;	
	struct record *temp,*trail;
	work =0;
	if(*start == NULL)
	{
		printf("\n there are no records to delete\n");
	}
	else
	{
		temp= *start;
		trail = NULL;
		if((strcmp(temp-> name,uname) == 0) && temp == *start)
		{
			*start = temp-> next;
			free(temp);
			temp = *start;
			work = 1;
		}
		while(temp != NULL)
		{
			if((strcmp(temp->name,uname) == 0) && temp == *start)
			{
				*start = temp-> next;
				free(temp);
				temp = *start;
				work = 1;
			}
			else if(strcmp(temp-> name,uname) == 0)
			{
				trail -> next = temp-> next;
				free(temp);
				temp = trail -> next;
				work = 1;
			}
			else
			{
				trail = temp;
				temp = temp-> next;
			}
		}
	}
	return work;
}

/*
function: printrecord

parameters: struct record *start, char name[]

description: it checks for the name and iterates through the linked list and if the name 
matches then it prints the record

return 1 if records printed
0 (no records or no match)

*/
int printRecord(struct record *start, char name[])
{
	struct record * iter;
	int work;
	iter = start;
	work =0;
	if(iter == NULL)
	{
		printf("there are no records to print\n");
	}
	while(iter != NULL)
	{
		work = 1;
		if(strcmp(iter -> name,name) ==0)
		{
			printf("name: %s \n",iter -> name);
			printf("address: %s \n", iter -> address);
			printf("telephone number: %s \n", iter-> telno);
			printf("yeare of birth: %d \n", iter-> yearofbirth);
			work = 1;
		}
		iter = iter-> next;
	}
	return work;
}
/*
function printallrecords

parameters: struct record *start

descrition: prints entire address book

*/
void printAllRecords(struct record *start)
{
	struct record  *iter;
	iter = start;
	if(iter == NULL)
	{
		printf("there are no records in the database to print\n");
	}
	while(iter != NULL)
	{
		printf("name: %s\n",iter -> name);
		printf("address: %s \n", iter -> address);
		printf("telephone number: %s \n", iter-> telno);
		printf("year of birth: %d \n\n", iter-> yearofbirth);
		iter = iter-> next;
	}

}
/*
function modifyrecord

parameters:struct record *start, char name[], char addr [] , char telno []

description: iterates through the linked list and if the name matches then the records will be modifed

return 1 worked
0 no matches or no records
*/
int modifyRecord(struct record *start, char name[], char addr [] , char telno [] )
{
	int work;
	struct record *iter;
	iter =start;
	work= 0;
	while( iter != NULL)
	{ 
		if(strcmp(iter -> name,name)==0)
		{ 
			strcpy(iter-> address,addr);
			strcpy(iter-> telno,telno);
			work = 1;
		}
		iter = iter-> next;
	}
	return work;
}


/*
function: writefile

parameters struct record *start,char fileout[]

description: after the program ends it writes all the data in the linked list into a txtfile called
output.txt 


*/
void writefile(struct record *start,char fileout[])
{
	struct record *iter;
	FILE *out;
	iter = start;
	out = fopen(fileout,"w");
	while(iter != NULL)
	{
		fprintf(out,"%s|",iter -> name);
		fprintf(out,"%s|",iter -> address);
		fprintf(out,"%d|",iter -> yearofbirth);
		fprintf(out,"%s|\n",iter ->telno);
		iter = iter->next;
	}
	fclose(out);
}

/*
function: readfile

parameters struct record **start, char filename[]

description: it reads the file and places the data into the struct record

*/
int readfile(struct record **start, char filename[])
{
	int character,IterArr;
	FILE *filep;
	char name[30];
	char address[80];
	int boo;
	char telno[23];
	char year[10];
	char space ='|';
	filep=fopen(filename,"r");
	boo = 0;
	IterArr = 0;
	if( filep == NULL){

	}
	else{
		while(boo == 0)
		{
			character = fgetc(filep);
			if (character == EOF)
			{
				break;
			}
			while(space != character)
			{
				name[IterArr] = character;
				character = fgetc(filep);
				IterArr ++;
			}
			name[IterArr]='\0';
			IterArr =0;
			character = fgetc(filep);
			while(space != character)
			{
				address[IterArr] = character;
				character = fgetc(filep);
				IterArr ++;
			}
			address[IterArr] ='\0';
			IterArr = 0;
			character = fgetc(filep);
			while(space != character)
			{
				year[IterArr] = character;
				character = fgetc(filep);
				IterArr ++;
			}
			year[IterArr]='\0';
			IterArr = 0;
			character = fgetc(filep);
			while(space != character)
			{
				telno[IterArr] = character;
				character = fgetc(filep);
				IterArr ++;
			}
			telno[IterArr]='\0';
			IterArr = 0;
			character = fgetc(filep);
			addRecord(start,name,address,atoi(year),telno);
		}

		fclose(filep);
	}
}

