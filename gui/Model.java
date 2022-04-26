import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {

    Connection c = null;
    Statement stmt = null;

    Model() 
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
            .getConnection("jdbc:postgresql://ec2-34-192-210-139.compute-1.amazonaws.com:5432/d9flbo3co4snf5?user=bqcxikchbtppcn&password=df563bd9a1bd352f70fa8f5df69f2ea627ebd295b471234f35b1cf44f012ab0d");

            System.out.println("Opened database successfully");
            stmt = c.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    //most_listened_artist_for_user: List of top 15 most listened artists for user; output is artistname, playcount
    public ResultSet most_listened_artist_for_user(String username)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT artists.artistname as ArtistName, SUM(usersong.number_of_times_played) as PlayCount FROM usersong, songs, users, artists WHERE Songs.songid = usersong.songid and Users.userid = usersong.userid and Artists.artistid = songs.artistid and Users.username = '"+username+"' GROUP BY ArtistName ORDER BY PlayCount desc LIMIT 15;" );                           

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    //most_listened_album_for_user: List of top 15 most listened albums for user; output is albumname, artistname, playcount
    public ResultSet most_listened_album_for_user(String username)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT albums.albumname as AlbumName, artists.artistname as ArtistName, SUM(usersong.number_of_times_played) as PlayCount FROM usersong, songs, users, artists, albums WHERE Artists.artistid = albums.artistid and albums.albumid = songs.albumid and songs.songid = usersong.songid and usersong.userid = users.userid and Users.username = '"+username+"' GROUP BY ArtistName, AlbumName ORDER BY PlayCount desc LIMIT 15;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    //most_listened_song_for_user: List of top 15 most listened songs for user; output is songname, artistname, playcount
    public ResultSet most_listened_song_for_user(String username)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT songs.songname as SongName, artists.artistname as ArtistName, SUM(usersong.number_of_times_played) as PlayCount FROM usersong, songs, users, artists WHERE Songs.songid = usersong.songid and Users.userid = usersong.userid and Artists.artistid = songs.artistid and Users.username = '"+username+"' GROUP BY SongName, ArtistName ORDER BY PlayCount desc LIMIT 15;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // album_track_listens_for_user: Listens on the tracks of an album for a user; outputs songname, playcount
    public ResultSet album_track_listens_for_user(String albumname, String username)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery("SELECT songs.songname As SongName, number_of_times_played As PlayCount FROM usersong, users, songs, artists, albums WHERE Artists.artistid = albums.artistid and albums.albumid = songs.albumid and songs.songid = usersong.songid and usersong.userid = users.userid and username='"+username+"' AND albumname='"+albumname+"' GROUP BY SongName, PlayCount ORDER BY PlayCount desc;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // top_tracks_of_artist_for_user: Top tracks from artist for user; output is songname, playcount
    public ResultSet top_tracks_of_artist_for_user(String artistname, String username)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT songs.songname as SongName, usersong.number_of_times_played as PlayCount FROM users, usersong, songs, artists, albums WHERE Users.userid = usersong.userid and usersong.songid=songs.songid and songs.artistid = artists.artistid and  Artists.artistid = albums.artistid and users.username='"+username+"' AND artists.artistname='"+artistname+"' GROUP BY SongName, PlayCount ORDER BY PlayCount desc;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // top_albums_of_artist_for_user: Most listened to albums from artist; outputs albumname, playcount
    public ResultSet top_albums_of_artist_for_user(String artistname, String username)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT albums.albumname as AlbumName, SUM(usersong.number_of_times_played) as PlayCount FROM users, usersong, songs, artists, albums WHERE Artists.artistid = albums.artistid and albums.albumid = songs.albumid and songs.songid = usersong.songid and usersong.userid = users.userid and users.username='"+username+"' AND artists.artistname='"+artistname+"' GROUP BY AlbumName ORDER BY PlayCount desc;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // who_scrobbled_artist_most: List of top listeners of this artist; output is username, playcount
    public ResultSet who_scrobbled_artist_most(String artistname)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery(
                "SELECT users.username as Username, SUM(usersong.number_of_times_played) as PlayCount FROM users, usersong, songs, artists WHERE users.userid = usersong.userid and usersong.songid = songs.songid and songs.artistid = artists.artistid and artistname = '"+artistname+"' GROUP BY users.username ORDER BY PlayCount desc LIMIT 15;" );
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // who_scrobbled_album_most: List of top listners of this album; output is username, playcount
    public ResultSet who_scrobbled_album_most(String albumname)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT users.username as Username, SUM(usersong.number_of_times_played) as PlayCount FROM users, usersong, songs, albums WHERE users.userid = usersong.userid and usersong.songid = songs.songid and songs.albumid = albums.albumid and albumname = '"+albumname+"' GROUP BY users.username ORDER BY PlayCount desc LIMIT 15;" );

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // who_scrobbled_song_most: List of top listners of this song; output is username, playcount
    public ResultSet who_scrobbled_song_most(String songname)
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery(
                "SELECT users.username as Username, number_of_times_played as PlayCount FROM users, usersong, songs WHERE users.userid = usersong.userid and usersong.songid = songs.songid and songname = '"+songname+"' ORDER BY number_of_times_played desc LIMIT 15;" );
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // most_listened_artist_for_everyone: List of top 15 most listened artists; outputs artistname, total playcount
    public ResultSet most_listened_artist_for_everyone()
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT artists.artistname as ArtistName, SUM(usersong.number_of_times_played) as TotalPlayCount FROM usersong, artists, songs WHERE Songs.songid = usersong.songid and artists.artistid = songs.artistid GROUP BY ArtistName ORDER BY TotalPlayCount desc LIMIT 15;" );

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // most_listened_album_for_everyone: List of top 15 most listened albums; outputs albumname, artistname, total playcount
    public ResultSet most_listened_album_for_everyone()
    {
        ResultSet rs = null;
        try
        {

            rs = stmt.executeQuery(
                "SELECT albums.albumname as AlbumName, artists.artistname as ArtistName, SUM(usersong.number_of_times_played) as TotalPlayCount FROM usersong, albums, artists, songs WHERE Songs.songid = usersong.songid and albums.albumid = songs.albumid and artists.artistid = albums.artistid GROUP BY AlbumName, ArtistName ORDER BY TotalPlayCount desc LIMIT 15;" );

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // most_listened_song_for_everyone: List of top 15 most listened songs; outputs songname, artistname, total playcount
    public ResultSet most_listened_song_for_everyone()
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery(
                "SELECT songs.songname as SongName, artists.artistname as ArtistName, SUM(usersong.number_of_times_played) as TotalPlayCount FROM usersong, songs, artists WHERE Songs.songid = usersong.songid and artists.artistid = songs.artistid GROUP BY SongName, ArtistName ORDER BY TotalPlayCount desc LIMIT 15;");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // greatest_amount_of_listeners_artist: List of top 15 most listened songs; outputs artistname, number of listeners
    public ResultSet greatest_amount_of_listeners_artist()
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT artists.artistname as ArtistName, COUNT(DISTINCT usersong.userid) as NumberofListeners FROM usersong, artists, songs WHERE Songs.songid = usersong.songid and artists.artistid = songs.artistid GROUP BY ArtistName ORDER BY NumberofListeners desc LIMIT 15;");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // greatest_amount_of_listeners_album: List of top 15 most listened albums; outputs albumname, artistname, number of listeners
    public ResultSet greatest_amount_of_listeners_album()
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT albums.albumname as AlbumName, artists.artistname as ArtistName, COUNT(DISTINCT usersong.userid) as NumberofListeners FROM usersong, albums, artists, songs WHERE Songs.songid = usersong.songid and albums.albumid = songs.albumid and artists.artistid = albums.artistid GROUP BY AlbumName, ArtistName ORDER BY NumberofListeners desc LIMIT 15;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // greatest_amount_of_listeners_song: List of top 15 most listened songs; outputs songname, artistname, number of listeners
    public ResultSet greatest_amount_of_listeners_song()
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery( 
                "SELECT songs.songname as SongName, artists.artistname as ArtistName, COUNT(DISTINCT usersong.userid) as NumberofListeners FROM usersong, songs, artists WHERE Songs.songid = usersong.songid and artists.artistid = songs.artistid GROUP BY SongName, ArtistName ORDER BY NumberofListeners desc LIMIT 15;");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rs;
    }


}