package com.example.lab.s;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String [] args) {
		//System.out.println("hello");
		Connection con = null;
		try {
			//SQlite JDBC check
			Class.forName("org.sqlite.JDBC");
			
			// file connect
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			// data find
			System.out.println("\n*** 데이터 조회 ***");
			Statement st = con.createStatement();
			String sql = "select * from g_artists";
			ResultSet r = st.executeQuery(sql);
			
			while (r.next()) {
				String id = r.getString("id");
				String name = r.getString("name");
				String a_type = r.getString("a_type");
				String a_year = r.getString("a_year");
				String debut = r.getString("debut");
				String regdate = r.getString("regdate");
				System.out.println(id + "|" + name + "|" + a_type + "|" + a_year + "|" + debut + "|" + regdate);
				// 내용 추가 가능...
				
			} 
			st.close();
			
			// 데이터 추가하기
			System.out.println("\n*** 새 데이터 추가 ***");
			Statement st1 = con.createStatement();
			String sql1 = "insert into g_artists (name, a_type, a_year, debut, regdate)" 
				+ " values ('예수전도단', '혼성', '1980년대', '1980년', datetime('now', 'localtime'));";
			int cnt = st1.executeUpdate(sql1);
			if (cnt > 0) {
				System.out.println("새로운 데이터가 추가되었습니다.");
			}
			else {
				System.out.println("[Error] 데이터 추가 오류!");
			}
			st1.close();
			
			
			// 데이터 수정
			System.out.println("\n*** 데이터 수정 ***");
			Statement st2 = con.createStatement();
			String sql2 = "update g_artists set a_year = '2000년대', '2010년대', '2020년' "
					+ "where id = 1;";
			int cnt2 = st2.executeUpdate(sql2);
			if (cnt2 >0) {
				System.out.println("데이터가 수정되었습니다!");
			}
			else {
				System.out.println("[Error] 데이터 수정 오류!");
			}
			st2.close();
			
			
			// 데이터 삭제
			System.out.println("\n*** Data Delete ***");
			Statement st3 = con.createStatement();
			String sql3 = "delete from g_artists where id = 10;";
			int cnt3 = st3.executeUpdate(sql3);
			if (cnt3 > 0) {
				System.out.println("데이터가 삭제되었습니다.");
			}
			else {
				System.out.println("[Error] 데이터 삭제 오류!");
			}
			st3.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
	}

}
