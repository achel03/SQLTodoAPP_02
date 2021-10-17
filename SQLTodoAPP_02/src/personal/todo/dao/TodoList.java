package personal.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.google.common.base.Strings;

import personal.todo.service.DbConnect;

public class TodoList {
	private Connection conn;

	public TodoList() {
		this.conn = DbConnect.getConnection();
	}

	public void importData(String string) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(string));
			String line;
			String sql = "insert into list (title, writer, memo, category, current_date, due_date, is_completed, importance)"+" values (?,?,?,?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine())!= null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String writer = st.nextToken();
				String memo = st.nextToken();
				String current_date = st.nextToken();
				String due_date = st.nextToken();
				int is_completed = Integer.parseInt(st.nextToken());
				int importance = Integer.parseInt(st.nextToken());
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, memo);
				pstmt.setString(4, category);
				pstmt.setString(5, current_date);
				pstmt.setString(6, due_date);
				pstmt.setInt(7, is_completed);
				pstmt.setInt(8, importance);
				int count = pstmt.executeUpdate();
				if(count>0)records++;
				pstmt.close();
			}
			System.out.println(records+" records read!!");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addItemL(TodoItem t) {
		String sql = "insert into list (title, writer, memo, category, current_date, due_date, is_completed, importance)"
				+" values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count =0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,t.getTitle());
				pstmt.setString(2,t.getWriter());
				pstmt.setString(3,t.getMemo());
				pstmt.setString(4,t.getCategory());
				pstmt.setString(5,t.getCurrent_date());
				pstmt.setString(6,t.getDue_date());
				pstmt.setInt(7, t.getIs_completed());
				pstmt.setInt(8,t.getImportance());
				count = pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	return 0;
	}
	
	public int editItemL(TodoItem t) {
		String sql = "update list set title=?, writer=?, memo=?, category=?, current_date=?, due_date=?, is_completed=?, importance=?"
				+" where id=?;";
		PreparedStatement pstmt;
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getWriter());
			pstmt.setString(3, t.getMemo());
			pstmt.setString(4, t.getCategory());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getDue_date());
			pstmt.setInt(7, t.getIs_completed());
			pstmt.setInt(8, t.getImportance());
			pstmt.setInt(9, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int getCountComp() {
		Statement pstmt;
		int count = 0;
		try {
			String sql = "SELECT count(id) FROM list WHERE is_completed = 1";
			pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		String sql = "SELECT * FROM list";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int importance = rs.getInt("importance");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, writer, memo, category, due_date, is_completed, importance);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<TodoItem> getListComp() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		String sql = "SELECT * FROM list WHERE is_completed=1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int importance = rs.getInt("importance");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, writer, memo, category, due_date, is_completed, importance);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	}
	public Boolean isDuplicate(String title) {
		for (TodoItem item : getList()) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}

	public int checkItem(int index) {
		String sql = "update list set is_completed=?"+ " where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int MtdeleteItem(String[] arr) {
		String sql = "delete from list where "+Strings.repeat("id=? or ", arr.length-1)+"id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i=0; i<arr.length; i++) {
				pstmt.setInt(i+1, Integer.parseInt(arr[i]));
			}
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int MtCompItem(String[] arr) {
		String sql = "update list set is_completed=?"+ " where "+Strings.repeat("id=? or ", arr.length-1)+"id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			for(int i=1; i<arr.length; i++) {
				pstmt.setInt(i+1, Integer.parseInt(arr[i]));
			}
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
