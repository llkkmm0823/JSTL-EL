package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ezen.dto.MemberDto;
import com.ezen.utill.DBMan;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() {return itc;}
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs =null;
	

	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		con = DBMan.getConnection();
		String sql ="select *from member where userid=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setEmail(rs.getString("email"));
				mdto.setAdmin(rs.getInt("admin"));
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {DBMan.close(con, pstmt, rs);
		}
		return mdto;
	}

}
