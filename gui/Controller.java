import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JTable;

public class Controller {
    Model model;
    Controller()
    {
        model = new Model();
    }

    public String[] most_listened_artist_for_user_Columns(String username)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.most_listened_artist_for_user(username);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;

    }

    public String[][] most_listened_artist_for_user(String username)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.most_listened_artist_for_user(username);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }

    public String[] most_listened_album_for_user_Columns(String username)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.most_listened_album_for_user(username);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;

    }
    
    public String[][] most_listened_album_for_user(String username)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.most_listened_album_for_user(username);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;

    }
    
    public String[] most_listened_song_for_user_Columns(String username)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.most_listened_song_for_user(username);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;

    }

    public String[][] most_listened_song_for_user(String username) 
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.most_listened_song_for_user(username);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }


    public String[] top_tracks_of_artist_for_user_Columns(String artistname, String username)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.top_tracks_of_artist_for_user(artistname, username);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;

    }
    
    public String[][] top_tracks_of_artist_for_user(String artistname, String username)
    {
         String[][] data =  null;
         try {

             ResultSet rs = model.top_tracks_of_artist_for_user(artistname, username);
             String[] columnNames = generateColumnNames(rs.getMetaData());
             data = generateTable(rs, columnNames, rs.getMetaData());
         } catch (Exception e2) {
             e2.printStackTrace();
             System.err.println(e2.getClass().getName()+": "+e2.getMessage());
             System.exit(0);
         }
         return data;

    }

    public String[] album_track_listens_for_user_Columns(String albumname, String username)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.album_track_listens_for_user(albumname, username);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;

    }

    public String[][] album_track_listens_for_user(String albumname, String username)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.album_track_listens_for_user(albumname, username);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;

    }
    
    
    public String[] top_albums_of_artist_for_user_Columns(String artistname, String username)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.top_albums_of_artist_for_user(artistname, username);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] top_albums_of_artist_for_user(String artistname, String username)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.top_albums_of_artist_for_user(artistname, username);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    
    public String[] who_scrobbled_artist_most_Columns(String artistname)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.who_scrobbled_artist_most(artistname);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] who_scrobbled_artist_most(String artistname)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.who_scrobbled_artist_most(artistname);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] who_scrobbled_album_most_Columns(String albumname)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.who_scrobbled_album_most(albumname);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] who_scrobbled_album_most(String albumname)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.who_scrobbled_album_most(albumname);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] who_scrobbled_song_most_Columns(String songname)
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.who_scrobbled_song_most(songname);
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] who_scrobbled_song_most(String songname)
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.who_scrobbled_song_most(songname);
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] most_listened_artist_for_everyone_Columns()
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.most_listened_artist_for_everyone();
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] most_listened_artist_for_everyone()
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.most_listened_artist_for_everyone();
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] most_listened_album_for_everyone_Columns()
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.most_listened_album_for_everyone();
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] most_listened_album_for_everyone()
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.most_listened_album_for_everyone();
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] most_listened_song_for_everyone_Columns()
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.most_listened_song_for_everyone();
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] most_listened_song_for_everyone()
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.most_listened_song_for_everyone();
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] greatest_amount_of_listeners_artist_Columns()
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.greatest_amount_of_listeners_artist();
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] greatest_amount_of_listeners_artist()
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.greatest_amount_of_listeners_artist();
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] greatest_amount_of_listeners_song_Columns()
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.greatest_amount_of_listeners_song();
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] greatest_amount_of_listeners_song()
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.greatest_amount_of_listeners_song();
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    public String[] greatest_amount_of_listeners_album_Columns()
    {
        String[] columnNames = null;
        try {

            ResultSet rs = model.greatest_amount_of_listeners_album();
            columnNames = generateColumnNames(rs.getMetaData());

        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }
    
    public String[][] greatest_amount_of_listeners_album()
    {
        String[][] data =  null;
        try {

            ResultSet rs = model.greatest_amount_of_listeners_album();
            String[] columnNames = generateColumnNames(rs.getMetaData());
            data = generateTable(rs, columnNames, rs.getMetaData());
        } catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return data;
    }
    
    
    
    

    private String[] generateColumnNames(ResultSetMetaData rsmd)

    {
        String[] columnNames = {};
        try{
            columnNames = new String[rsmd.getColumnCount()];
            for(int i=1;i<=rsmd.getColumnCount();i++)
            {
                columnNames[i-1] = rsmd.getColumnName(i);
                System.out.println(columnNames[i-1]);
            }
        }

        catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        return columnNames;
    }

    private String[][] generateTable(ResultSet rs, String[] columnNames, ResultSetMetaData rsmd)

    {
        ArrayList<String[]> list = new ArrayList<>();
        String[] rowData = new String[columnNames.length];
        int count = 0;
        try{
            while(rs.next())
            {
                rowData = new String[columnNames.length];
                for(int i=0;i<columnNames.length;i++)
                {
                    rowData[i] = rs.getString(rsmd.getColumnName(i+1));
                }
                list.add(rowData);

                count++;
            }
            System.out.println("Count: "+count);
        }

        catch (Exception e2) {
            e2.printStackTrace();
            System.err.println(e2.getClass().getName()+": "+e2.getMessage());
            System.exit(0);
        }
        String[][] data = new String[list.size()][columnNames.length];
        for(int i = 0; i<list.size();i++)
        {
            for(int j=0;j<columnNames.length;j++)
            {
                data[i][j] = (list.get(i))[j];
            }
        }

        return data;
    }

}
