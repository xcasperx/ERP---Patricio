/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorEconomico;

/**
 *
 * @author patricio
 */
public class IndicadorEconomicoSemanalBean {

    public int id;
    public float bencina93;
    public float bencina95;
    public float bencina97;
    public float diesel;
    public String publicTime;

    public IndicadorEconomicoSemanalBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBencina93() {
        return bencina93;
    }

    public void setBencina93(float bencina93) {
        this.bencina93 = bencina93;
    }

    public float getBencina95() {
        return bencina95;
    }

    public void setBencina95(float bencina95) {
        this.bencina95 = bencina95;
    }

    public float getBencina97() {
        return bencina97;
    }

    public void setBencina97(float bencina97) {
        this.bencina97 = bencina97;
    }

    public float getDiesel() {
        return diesel;
    }

    public void setDiesel(float diesel) {
        this.diesel = diesel;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }
}
