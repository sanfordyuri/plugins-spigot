package me.impalinha.impcoins.Utils;

import me.impalinha.impcoins.ImpCoins;

public class Constants {

    //Messages
    public static String PREFIX = "§2[Banco] §f";
    public static String VAULT_NOT_FOUND = "Dependência Vault não encontrada, desativando plugin.";
    public static String VAULT_FOUND = "A Dependência Vault foi detectada com sucesso.";
    public static String CHECKING_VAULT = "Verificando dependência Vault...";
    public static String ECO_OK = "Sistema de Economia ativado com sucesso.";
    public static String CONNECTION_OK = "Conexão com o banco de dados OK.";
    public static String CONNECTION_OFF = "Conexão com o banco de dados Fechada. Desabilitando plugin...";
    public static String SEND_DATA_DB = "Enviando dados para o Banco...";
    public static String SEND_DATA_FINISHED = "Envio de dados finalizado.";


    //DataBase
    public static String DATASOURCE = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";


    //Querys
    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TB_Coins(UUID varchar(36), Name VARCHAR(16), Amount double)";
    public static String INSERT = "INSERT INTO TB_Coins(UUID, Name, Amount) VALUES (?,?,?)";
    public static String SELECT_MONEY = "SELECT AMOUNT FROM TB_Coins WHERE UUID = ?";
    public static String UPDATE_MONEY = "UPDATE `TB_Coins` SET `Amount`= ? WHERE UUID = ?";
    public static String SELECT = "SELECT * FROM TB_Coins";

    //Utils
    public static String PERMISSION = "ImpCoin.admin";


}
