package edu.saic.twodfire.util;

import java.io.*;
import java.sql.*;

public class DoSQLScript {
    
    //private Connection _conn;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //int argc = args.length;
	    //if (argc < 1) {
	    //  System.err.println("Usage: java DoSQLScript [sql_file_name]");
	    //  System.err.println(" e.g.: java DoSQLScript all.sql");
	    //  return;
	    //}
	    DoSQLScript dss = new DoSQLScript();
	    //dss.insertData(args[0]);
	    String path = "/Desktop/";
	    String[] fnames = {
	    		"insert.instance.hsql.txt",
	    		"insert.instancebill.hsql.txt",
	    		"insert.menu.hsql.txt",
	    		"insert.orderinfo.hsql.txt",
	    		"insert.totalpay.hsql.txt"
	    		
	    };
	    for (String s: fnames) {
	    	System.err.println("Processing "+s+"...");
	    	dss.insertData(path+s);
	    }
	}

	public void insertData(String fname)
	{
        Connection conn = null;
	    File fi = new File(fname);
	    if (fi.exists()) {
	      try {  // get the stock object
	    	  java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fname));
	    	  String line="";
	    	  int count=0;
                  conn = DBUtil.getDBConnection();
	    	  while ((line=br.readLine()) != null) {
                                //conn.setAutoCommit(false);
	  			Statement stmt = conn.createStatement();
	  			try {  // get the stock object
	  				count++;
	  				stmt.execute(line);
                                        //if (count % 100 == 0) {
                                        //         conn.commit();
                                        //}
	  				if (count % 100 == 0) {
	  					System.err.print("#");
	  				}
	  				//if (count % 100000 == 0) {
                                        //        //conn.close();
                                        //        conn = null;
	  				//}
	  			} catch (java.sql.SQLException e) {
                                        System.out.println();
	  				System.err.println("SQLException: "+e);
                                        System.out.println("Current data (line # = "+count+"): "+line);
	  			}
	  			// keep running: next SQL command
	    	  }
                  // the final commit
                  //conn.commit();
	    	  System.out.println("...done.");
	    	  br.close();
	    	  conn.close();
	      } catch (Exception e) {
	        System.err.println(e);
	      }
	    }
		
	}

}
