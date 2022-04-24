/*--------------------------------------------------------------------------
 * Final Project, CSE 412: sorter.cpp
 * Group 19
 * Created by Claire Cirelli, cmcirell
 * Zou!!!!
 * Description: This process automates the Artists, Albums, Songs, Usersongs 
                databases. Note that the User table is not generated here
                because we will make it manually.
 --------------------------------------------------------------------------*/

/*------------------------------LIBS & IMPORTS-----------------------------*/
#include <iostream>
#include <cstdlib>
#include "sorter.h"
#include <algorithm>
#include <fstream>
using namespace std;
//#define DEBUG

/*-----------------------------GLOBAL VARIABLES-----------------------------*/
std::vector<struct artist*> Artists;
std::vector<struct album*> Albums;
std::vector<struct song*> Songs;
std::vector<struct usersong*> UserSongs;
std::vector<struct scrobble*> Lastfm;

// Declare global variables
int songGlob, artGlob, albGlob = 0;
int userID = 3; // change for each .csv


// Before this step, 
// 1) All of the csv files have been combined into one file with unique IDs for each user
// 2) Parse lastfm .csv files: alphebetize by artist, then album, then song, then userID, remove duplicates & 
//    record playcount(# of duplicates)
// 3) Now the table has userid, artistname, albumname, songname, playcount


/*-----------------------------SORTING FUNCTIONS-----------------------------*/
void Sorter::sorting_csv_to_lastfm()
{
    // int userID;
    // std::string artistName;
    // std::string albumName;
    // std::string songName;
    // int playCount;

    fstream f_in;

    f_in.open("all_data.csv", ios::in);
    
    std::string input = "";

    //cin.getline(userID, artistName, albumName, songName, playCount)
    while (getline(f_in, input))
    {
        std::vector<std::string> inputs;
        std::string delimiter = ",";
        size_t pos = 0;
        std::string token;

        while ((pos = input.find(delimiter)) != std::string::npos) {
            token = input.substr(0, pos);
            //std::cout << token << std::endl;
            inputs.push_back(token);
            input.erase(0, pos + delimiter.length());
        }

        inputs.push_back(input);

        struct scrobble * new_scrob = new scrobble;

        new_scrob->userid = stoi(inputs[0]);
        new_scrob->artistname = inputs[1];
        new_scrob->albumname = inputs[2];
        new_scrob->songname = inputs[3];
        new_scrob->playcount = stoi(inputs[4]);

        Lastfm.push_back(new_scrob);
    }
    f_in.close();

    // while (getline(userID, artistName, albumName, songName, playCount))
    // {
    //     struct scrobble * new_scrob = new scrobble;

    //     new_scrob->userid = userID;
    //     new_scrob->artistname = artistName;
    //     new_scrob->albumame = albumName;
    //     new_scrob->songname = songName;
    //     new_scrob->playcount = playCount;

    //     Lastfm.push_back(new_scrobble);
    // }

} // end of sorting_csv_to_lastfm


void Sorter::sorting_lastfm_to_vectors()
{
    for (std::vector<struct scrobble*>::const_iterator lfm = Lastfm.begin(); lfm != Lastfm.end(); ++lfm)
    {
        // this is so we iterate through all of the items 
        int songID = 0;
        int artistID = 0;
        int albumID = 0;
        int userID = (*lfm)->userid;

        std::string songName = (*lfm)->songname;
        std::string albumName =  (*lfm)->albumname;
        std::string artistName = (*lfm)->artistname;
        int playCount = (*lfm)->playcount;

        /*~~~~~~~~~~~~~~~ ARTISTS ~~~~~~~~~~~~~~~*/
        for (std::vector<struct artist*>::const_iterator item = Artists.begin(); item != Artists.end(); ++item)
        {
            if ((*item)->artistname == artistName) // artist already exists
            {
                artistID = (*item)->artistid;
            }
            else
            {
                // do nothing
            }
        }
        if (artistID == 0) // artist wasn't found; make new unique artist
        {
            // Make new vector item for Artists
            struct artist * new_art = new artist;

            artistID = ++artGlob;

            // Assign values to the new vector
            new_art->artistid = artistID;
            new_art->artistname = artistName;
            new_art->age = 0;
            new_art->gender = 3;

            // Append new item to the end of the Artists vector 
            Artists.push_back(new_art);

            // At this point, we know if it's a new artist it has to be a new album and new song
            // So we can assign the rest of the values for album and song and exit the loop

            // New vector item for Albums
            struct album * new_alb = new album;
            
            albumID = ++albGlob;

            // Assign values to the new vector
            new_alb->albumid = albumID;
            new_alb->albumname = albumName;
            new_alb->artistid = artistID;
            new_alb->year = 0;

            // Append new item to the end of the Albums vector 
            Albums.push_back(new_alb);

            // New vector for Songs
            struct song * new_song = new song;

            songID = ++songGlob;

            // Assign values to the new vector
            new_song->songid = songID;
            new_song->songname = songName;
            new_song->artistid = artistID;
            new_song->albumid = albumID;

            // Append song to end of vector
            Songs.push_back(new_song);

            // New vector for Usersongs
            struct usersong * new_us = new usersong;

            // Assign values to the new vector
            new_us->playcount = playCount;
            new_us->userid = userID;
            new_us->songid = songID;

            // Append usersong to end of vector
            UserSongs.push_back(new_us);

            continue; // exit for loop
        }

        /*~~~~~~~~~~~~~~~ ALBUMS ~~~~~~~~~~~~~~~*/
        for (std::vector<struct album*>::const_iterator item = Albums.begin(); item != Albums.end(); ++item)
        {
            if ( ((*item)->albumname == albumName) && ( (*item)->artistid == artistID) ) // album + artist already exists
            {
                albumID = (*item)->albumid;
            }
            else
            {
                // do nothing
            }
        }
        if (albumID == 0) // unique album
        {
            // Make new vector item for Albums
            struct album * new_alb = new album;
            
            albumID = ++albGlob;

            // Assign values to the new vector
            new_alb->albumid = albumID;
            new_alb->albumname = albumName;
            new_alb->artistid = artistID;
            new_alb->year = 0;

            // Append new item to the end of the Albums vector 
            Albums.push_back(new_alb);

            // ---- If it's a new album, it must be a new song ----

            // New vector for Songs
            struct song * new_song = new song;

            songID = ++songGlob;

            // Assign values to the new vector
            new_song->songid = songID;
            new_song->songname = songName;
            new_song->artistid = artistID;
            new_song->albumid = albumID;

            // Append song to end of vector
            Songs.push_back(new_song);

            // New vector for Usersongs
            struct usersong * new_us = new usersong;

            // Assign values to the new vector
            new_us->playcount = playCount;
            new_us->userid = userID;
            new_us->songid = songID;

            // Append usersong to end of vector
            UserSongs.push_back(new_us);

            continue; // exit for loop
        }

        /*~~~~~~~~~~~~~~~ SONGS ~~~~~~~~~~~~~~~*/
        for (std::vector<struct song*>::const_iterator item = Songs.begin(); item != Songs.end(); ++item)
        {
            if ( ((*item)->songname == songName) && ((*item)->albumid == albumID) && ((*item)->artistid == artistID) ) // song already exists
            {
                songID = (*item)->songid;
            }
            else
            {
                // do nothing
            }
        }
        if (songID == 0) // unique song
        {
            // New vector for Songs
            struct song * new_song = new song;

            songID = ++songGlob;

            // Assign values to the new vector
            new_song->songid = songID;
            new_song->songname = songName;
            new_song->artistid = artistID;
            new_song->albumid = albumID;

            // Append song to end of vector
            Songs.push_back(new_song);

            // New vector for Usersongs
            usersong * new_us = new usersong;

            // Assign values to the new vector
            new_us->playcount = playCount;
            new_us->userid = userID;
            new_us->songid = songID;

            // Append usersong to end of vector
            UserSongs.push_back(new_us);

            continue;
        }

        // At this point, we have realized that this song/alb/art exists in the database
        // but we still need to add it to the Usersongs vector

        // New vector for Usersongs
        struct usersong * new_us = new usersong;

        userID = (*lfm)->userid;
        playCount = (*lfm)->playcount;

        // Assign values to the new vector
        new_us->playcount = playCount;
        new_us->userid = userID;
        new_us->songid = songID;

        // Append usersong to end of vector
        UserSongs.push_back(new_us);
    }
} // end of sorting lastfm_to_vectors


/*-----------------------------OUTPUT FUNCTIONS-----------------------------*/

void Sorter::vectors_to_sql_commmands()
{
    for (std::vector<struct artist*>::const_iterator i = Artists.begin(); i != Artists.end(); ++i)
    {
        cout << "insert into artists(artistid, artistname, age, gender) values (" << (*i)->artistid << "," << (*i)->artistname << "," << (*i)->age << "," << (*i)->gender << ");\n";
    }
    for (std::vector<struct album*>::const_iterator i = Albums.begin(); i != Albums.end(); ++i)
    {
        cout << "insert into albums(albumid, albumname, artistid, year) values (" << (*i)->albumid << "," << (*i)->albumname << "," << (*i)->artistid << "," << (*i)->year << ");\n";
    }
    for (std::vector<struct song*>::const_iterator i = Songs.begin(); i != Songs.end(); ++i)
    {
        cout << "insert into songs(songid, songname, artistid, albumid) values (" << (*i)->songid << "," << (*i)->songname << "," << (*i)->artistid << "," << (*i)->albumid << ");\n";
    }
    for (std::vector<struct usersong*>::const_iterator i = UserSongs.begin(); i != UserSongs.end(); ++i)
    {
        cout << "insert into usersong(songid, userid, number_of_times_played) values (" << (*i)->songid << "," << (*i)->userid << "," << (*i)->playcount << ");\n";
    }

} // end of vectors_to_sql_commands()

void Sorter::vectors_to_csv()
{
    std::ofstream artFile("Artists.csv");
    for (std::vector<struct artist*>::const_iterator i = Artists.begin(); i != Artists.end(); ++i)
    {
        artFile << (*i)->artistid << "," << (*i)->artistname << "," << (*i)->age << "," << (*i)->gender << "\n";
    }
    artFile.close();

    std::ofstream albFile("Albums.csv");
    for (std::vector<struct album*>::const_iterator i = Albums.begin(); i != Albums.end(); ++i)
    {
        albFile << (*i)->albumid << "," << (*i)->albumname << "," << (*i)->artistid << "," << (*i)->year << "\n";
    }
    albFile.close();

    std::ofstream songFile("Songs.csv");
    for (std::vector<struct song*>::const_iterator i = Songs.begin(); i != Songs.end(); ++i)
    {
        songFile << (*i)->songid << "," << (*i)->songname << "," << (*i)->artistid << "," << (*i)->albumid << "\n";
    }
    songFile.close();

    std::ofstream usersongFile("UserSong.csv");
    for (std::vector<struct usersong*>::const_iterator i = UserSongs.begin(); i != UserSongs.end(); ++i)
    {
        usersongFile << (*i)->songid << "," << (*i)->userid << "," << (*i)->playcount << "\n";
    }
    usersongFile.close();

} // end of vectors_to_csv()

/*-----------------------------TEST PRINT FUNCTIONS-----------------------------*/

void Sorter::print_Lastfm()
{
    for (std::vector<struct scrobble*>::const_iterator i = Lastfm.begin(); i != Lastfm.end(); ++i)
    {
        cout << (*i)->userid << ", " << (*i)->artistname << ", " << (*i)->albumname << ", " << (*i)->songname << ", " << (*i)->playcount << ";\n";
    }
    cout << endl;
} // end of print_Lastfm

void Sorter::print_Artists()
{
    for (std::vector<struct artist*>::const_iterator i = Artists.begin(); i != Artists.end(); ++i)
    {
        cout << (*i)->artistid << ", " << (*i)->artistname << ";\n";
    }
    cout << endl;
} // end of print_Artists

void Sorter::print_Albums()
{
    for (std::vector<struct album*>::const_iterator i = Albums.begin(); i != Albums.end(); ++i)
    {
        cout << (*i)->albumid << ", "  << (*i)->albumname << ";\n";
    }
    cout << endl;
} // end of print_Albums

void Sorter::print_Songs()
{
    for (std::vector<struct song*>::const_iterator i = Songs.begin(); i != Songs.end(); ++i)
    {
        cout << (*i)->songid << ", "  << (*i)->songname << ";\n";
    }
    cout << endl;
} // end of print_Songs

void Sorter::print_UserSongs()
{
    for (std::vector<struct usersong*>::const_iterator i = UserSongs.begin(); i != UserSongs.end(); ++i)
    {
        cout << (*i)->songid << ", "  << (*i)->userid << ";\n";
    }
    cout << endl;
} // end of print_UserSongs



int main()
{
    Sorter sorter;

    // struct scrobble * new1 = new scrobble;
    // struct scrobble * new2 = new scrobble;
    // struct scrobble * new3 = new scrobble;
    // struct scrobble * new4 = new scrobble;
    // struct scrobble * new5 = new scrobble;

    // new1->userid = 1;
    // new1->artistname = "The Strokes";
    // new1->albumname = "The New Abnormal";
    // new1->songname = "Selfless";
    // new1->playcount = 8;

    // new2->userid = 2;
    // new2->artistname = "The Strokes";
    // new2->albumname = "The New Abnormal";
    // new2->songname = "Selfless";
    // new2->playcount = 3;

    // new3->userid = 2;
    // new3->artistname = "The Strokes";
    // new3->albumname = "The New Abnormal";
    // new3->songname = "Brooklyn Bridge to Chorus";
    // new3->playcount = 1;

    // new4->userid = 3;
    // new4->artistname = "Lorde";
    // new4->albumname = "Pure Heroine";
    // new4->songname = "Buzzcut Season";
    // new4->playcount = 1;

    // new5->userid = 4;
    // new5->artistname = "The Strokes";
    // new5->albumname = "The New Abnormal";
    // new5->songname = "Brooklyn Bridge to Chorus";
    // new5->playcount = 5;

    // Append song to end of vector
    // Lastfm.push_back(new1);
    // Lastfm.push_back(new2);
    // Lastfm.push_back(new3);
    // Lastfm.push_back(new4);
    // Lastfm.push_back(new5);


    sorter.sorting_csv_to_lastfm();
    sorter.sorting_lastfm_to_vectors();
    sorter.vectors_to_csv();
}




/*
Query for Album Insert:
insert into albums(albumid, albumname, artistid, year) values (1, 'Red', 1, 2021);
Query for Songs Insert:
insert into songs(songid, songname, artistid, albumid) values (1, 'All Too Well', 1, 1);
Query for UserSong Insert:
insert into usersong(songid, userid, number_of_times_played) values (1, 1, 12);
Query for Artists Insert:
insert into artists (artistid, artistname,age, gender) values (1,'Taylor Swift', 32, 0);
Query for User Insert:
insert into users (userid, username, age, gender) values (1, 'ladyzuko', 21, 0);
/*