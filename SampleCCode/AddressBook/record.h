/*
file record.h

descritpion: contains the definition of stuct record

*/
#ifndef record_h
#define reocrd_h


struct record

{

     char           name[25];

     char           address[80];

     int            yearofbirth;
       char           telno[15];
       struct record* next;

};
#endif