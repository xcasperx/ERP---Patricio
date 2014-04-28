/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoMensual;

/**
 *
 * @author patricio
 */
public class IndicadorEconomicoMensualBean implements java.io.Serializable {

    public int id;
    public float utm;
    public float ipc;
    public String publicTime;
    public String updateTime;
    public int idUser;
    public String username;

    public IndicadorEconomicoMensualBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getUtm() {
        return utm;
    }

    public void setUtm(float utm) {
        this.utm = utm;
    }

    public float getIpc() {
        return ipc;
    }

    public void setIpc(float ipc) {
        this.ipc = ipc;
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
