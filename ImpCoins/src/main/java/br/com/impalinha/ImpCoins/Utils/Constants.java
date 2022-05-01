package br.com.impalinha.ImpCoins.Utils;

public class Constants {

    //Messages
    public static String VAULT_NOT_FOUND = "ImpCoins desativado. Motivo: Dependência Vault não encontrada.";

    //DataBase
    public static String DATASOURCE = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
    public static String SERVER_NAME = "116.203.228.35";
    public static String PORT = "3306";
    public static String DATABASE = "s10506_ImpCoins";
    public static String USER = "u10506_YYmRHQp5FA";
    public static String PASSWORD = "hZ.Eu@qDt+j4cyhCMbzNij=c";

    //Querys
    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TB_Coins(UUID varchar(36), Name VARCHAR(16), Amount double)";

}
