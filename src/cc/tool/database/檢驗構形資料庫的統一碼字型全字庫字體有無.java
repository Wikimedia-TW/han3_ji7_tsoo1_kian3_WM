package cc.tool.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import cc.setting.piece.整合字體;

/**
 * 檢驗構形資料庫的統一碼字型全字庫字體有無。才知影愛予佗一个編碼優先。
 * 
 * @author Ihc
 */
public class 檢驗構形資料庫的統一碼字型全字庫字體有無
{
	/** 佮主機資料庫的連線 */
	PgsqlConnection 連線;

	/**
	 * 主函式。
	 * 
	 * @param args
	 *            程式參數
	 */
	public static void main(String[] args)
	{
		檢驗構形資料庫的統一碼字型全字庫字體有無 檢驗工具 = new 檢驗構形資料庫的統一碼字型全字庫字體有無();
		檢驗工具.執行();
		return;
	}

	/** 做代誌！！ */
	private void 執行()
	{
		整合字體 宋體 = 整合字體.提著宋體字體();
		整合字體 楷體 = 整合字體.提著楷體字體();
		System.out.println("開始嘍～～ 時間：" + System.currentTimeMillis());
		連線 = new PgsqlConnection();
		int 宋體字型數 = 0;
		int 楷體字型數 = 0;
		try
		{
			// 更新連線.executeUpdate("DELETE FROM \"漢字組建\".\"檢字表\"");
			String selectAllQuery = "SELECT \"Unicode\""
					+ " FROM \"構形資料庫\".\"檢字表\" "
					+ " WHERE \"Unicode\" IS NOT NULL "
					+ " ORDER BY \"編號\" ASC"
			// + " LIMIT 100"
			;
			ResultSet allDataNumber = 連線.executeQuery(selectAllQuery);
			while (allDataNumber.next())
			{
				int 統一碼 = Integer.parseInt(allDataNumber.getString("Unicode"),
						16);
				if (宋體.有這个字型無(統一碼))
					宋體字型數++;
				else
					System.out.println("宋體無"
							+ allDataNumber.getString("Unicode"));
				if (楷體.有這个字型無(統一碼))
					楷體字型數++;
				else
					System.out.println("楷體無"
							+ allDataNumber.getString("Unicode"));

			}
		}
		catch (SQLException e)
		{
			System.err.println("巡訪時發現錯誤！！！ ");
			e.printStackTrace();
		}
		System.out.println("結束嘍～～ 時間：" + System.currentTimeMillis());
		System.out.println("字型數=" + 宋體字型數 + " " + 楷體字型數);
	}

}
