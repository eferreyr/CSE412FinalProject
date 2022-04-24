/*--------------------------------------------------------------------------
 * Final Project, CSE 412: sorter.h
 * Group 19
 * Created by Claire Cirelli, cmcirell
 * Zou!!!!
 --------------------------------------------------------------------------*/

#include <string>
#include <vector>

#ifndef __SORTER_H__
#define __SORTER_H__

/* 
    Artists(age,artistid, artistname, gender)
    Albums(albumid, albumname, artistid, year)
    Songs (albumid, artistid,songid, songname)
    Usersong (userid, songid, playcount)
    Lastfm (userid, artistname, albumname, songname, playcount)
*/

struct artist
{
  int artistid;
  std::string artistname;
  int age;
  int gender;
};

struct album
{
  int artistid;
  int albumid;
  std::string albumname;
  int year;
};

struct song
{
  int artistid;
  int albumid;
  int songid;
  std::string songname;
};

struct usersong
{
  int songid;
  int userid;
  int playcount;

};

struct scrobble
{
  int userid;
  std::string artistname;
  std::string albumname;
  std::string songname;
  int playcount;
};


class Sorter
{
  public:
    void sorting_csv_to_lastfm();
    void sorting_lastfm_to_vectors();
    void vectors_to_sql_commmands();    
    void vectors_to_csv();

    void print_Lastfm();
    void print_Artists();
    void print_Albums();
    void print_Songs();
    void print_UserSongs();
};

#endif