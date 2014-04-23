/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorEconomico;

/**
 *
 * @author patricio
 */
public class IndicadorEconomicoMensualBean implements java.io.Serializable {

    public int id;
    public float utm;
    public float ipc;
    public String publicTime;

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
}
