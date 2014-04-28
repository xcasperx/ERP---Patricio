/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoDiario;

/**
 *
 * @author patricio
 */
public class IndicadorEconomicoDiarioBean implements java.io.Serializable {

    public int id;
    public Float uf;
    public Float dolar;
    public Float euro;
    public String publicTime;
    public String updateTime;
    public int idUser;
    public String username;

    public IndicadorEconomicoDiarioBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getUf() {
        return uf;
    }

    public void setUf(Float uf) {
        this.uf = uf;
    }

    public Float getDolar() {
        return dolar;
    }

    public void setDolar(Float dolar) {
        this.dolar = dolar;
    }

    public Float getEuro() {
        return euro;
    }

    public void setEuro(Float euro) {
        this.euro = euro;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
