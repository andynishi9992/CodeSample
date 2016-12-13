/*
Name: Andrew Nishimura

Assignment: Project 1

Instructor: Ravi Narayan

Descrption: this program shows a menu and takes user input  from comand line
to create a record

*/

# include <stdio.h>
# include <string.h>
# include <stdlib.h>
# include "record.h"

void showmenu();
void takeusercommand( char[]);
struct record* executeuserchoice(struct record *);
void getfield(char [], int*);
int runprogram(int , char* []);
int addRecord (struct record **,char [],char [],int, char []);

int printRecord (struct record *, char []);   

int modifyRecord (struct record *, char [],char [], char []);  

void printAllRecords(struct record *);

int deleteRecord(struct record **, char []);

int readfile(struct record **, char []);

void writefile(struct record *, char []); 
/* debug global variable*/
int debug;



int main(int argc , char *argv[])
{
	struct record* start = NULL;
	if (argc == 2  && strcmp(argv[1],"debug") == 0)
	{
        printf("you are in debug mode\n");
		debug = 1;
		printf("executeuserchoice()\n readfile(&start, output.txt\n");
		readfile(&start,"output.txt");
		start = executeuserchoice(start);
		printf("writefile(start,output.txt)");
		
		
		writefile(start,"output.txt");
		
	}

	else if(argc ==1)
	{
		printf("you are in normal mode\n");
		readfile(&start,"output.txt");
		debug =0;
		start = executeuserchoice(start);
		
        writefile(start,"output.txt");
		
		
	}
	else{
		printf("your main function argument is invalid\n");
	}


}
/*
functionname: showmenu

description: this function shows the menu to the user
*/

void showmenu()
{
	if(debug==1)
	{
		printf("using showmenu()\n");
	}
	printf("These choices are available\n");
	printf( "1. Add a new record\n");
	printf("2.modify a record\n");
	printf("3. print information\n");
	printf("4. print all information\n");
	printf("5. delete a record\n");
	printf("6.  quit\n");
	printf("enter your choice either 1,2,3,4,5,6\n");
}

/*
function: takeusercommande

description: takes in the users choice for the menu

return userchoice

*/

void takeusercommand(char choice[])
{
	char input;
	if(debug == 1)
	{
		printf("takeusercommand()\n");
	}
	choice[0]= fgetc(stdin);

}



/*
Function:executeuserchoice


Descripton: this function takes in the user input on their choice 
of the menu

parameters struct record *start

return struct record* the point to the start of the linked list in the heap
*/
struct record * executeuserchoice(struct record *start)
{ 
	int arrayindex,year,work;
	char userinformation[100];
	char name[30];
	char choice[2];
	char telno[20];
	char address[80];
	arrayindex=0;
	while(strcmp(choice,"6") != 0)
	{
		if(debug ==1)
		{
			printf("invoking showmenu()\n");
		}
		showmenu();
		choice[0] = fgetc(stdin);
		choice[1] = '\0';
		arrayindex = 0;
		work = 0;
		fflush(stdin);

		if(strcmp(choice,"1") == 0)/* add a record*/
		{   
			printf("what is your name (press <TAB> and then <enter> when completed)\n");
			if(debug == 1)
			{
				printf("using getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(name,userinformation);
			arrayindex=0;
			printf("what is your address (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf(" using getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(address,userinformation);
			arrayindex=0;
			printf("what is your phonenumber (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf(" using getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(telno,userinformation);
			arrayindex=0;
			printf("what is your year of birth? press <enter> when completed\n");
			if(debug ==1)
			{
				printf("using scanf(int,&year);\n");
			}
			scanf("%d",&year);
			if(debug ==1)
			{
				printf("using addRecord(&start,name,address,year,telno);\n");
			}
			addRecord(&start,name,address,year,telno);
			printf("you have successfully added a new record\n");
			fflush(stdin);
		}
		else if(strcmp(choice,"2")==0)/*modify a record must take name*/
		{   
			printf("please enter your name (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf("using getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(name,userinformation);
			arrayindex=0;
			printf("what is your address (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf("using getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(address,userinformation);
			arrayindex=0;
			printf("what is your phonenumber (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf("using getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(telno,userinformation);
			arrayindex=0;
			if(debug ==1)
			{
				printf("using modifyRecord(start,name,address,telno);\n");
			}
			work = modifyRecord(start,name,address,telno);
			if(work == 0)
			{
				printf("your name did not match any of our records\n");
			}
			else{
				printf("you have sucessfully modified your record\n");
			}
			fflush(stdin);
		}
		else if(strcmp(choice,"3")==0)/*print a record must take name*/
		{
			printf("please enter your name (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf("getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(name,userinformation);
			arrayindex=0;
			printf("here are the records\n\n");
			if(debug ==1)
			{
				printf(" using printRecord(start,name);\n");
			}
			work =printRecord(start,name);
			if(work ==0)
			{
				printf("your name did not match any of our records\n");
			}
			fflush(stdin);
		}
		else if(strcmp(choice,"4")==0)/*print all records*/
		{
			if(debug ==1)
			{
				printf(" using printAllRecords(start);\n");
			}
			printAllRecords(start);

		}
		else if(strcmp(choice,"5")==0)/* delete record*/
		{
			printf("please enter your name (press <TAB> and then <enter> when completed)\n");
			if(debug ==1)
			{
				printf(" getfield(userinformation,&arrayindex);\n");
			}
			getfield(userinformation,&arrayindex);
			userinformation[arrayindex] = '\0';
			strcpy(name,userinformation);
			arrayindex=0;
			if(debug ==1)
			{
				printf("using deleteRecord(&start,name);\n");
			}
			work = deleteRecord(&start,name);
			if(work ==0)
			{
				printf("there are no records of your name \n");

			}
			else{
				printf("you successfully deleted a record\n");
			}
			fflush(stdin);
		}
		else if(strcmp(choice,"6")==0)
		{
			printf("program aborted\n");

			break;
		}
		else{
			printf("please enter a valid command \n");
		}

	}
	return start;
}

/*
function: getfield

descprition: takes in the users name, adress and phonenumber

*/

void getfield(char userinformation[], int *arrayindex)
{

	int enter,boolean,currentindex;
	currentindex= *arrayindex;
	boolean =0;
	while(boolean == 0)
	{enter = fgetc(stdin);
	if(enter =='\t')
	{
		break;
	}
	userinformation[currentindex] = enter;
	currentindex = currentindex +1;
	}
	*arrayindex = currentindex;
}

















