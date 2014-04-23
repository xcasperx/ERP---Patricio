/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorEconomico;

/**
 *
 * @author patricio
 */
public class IndicadorEconomicoDiarioBean implements java.io.Serializable {

    public int id;
    public float uf;
    public float dolar;
    public float euro;
    public String publicTime;

    public IndicadorEconomicoDiarioBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getUf() {
        return uf;
    }

    public void setUf(float uf) {
        this.uf = uf;
    }

    public float getDolar() {
        return dolar;
    }

    public void setDolar(float dolar) {
        this.dolar = dolar;
    }

    public float getEuro() {
        return euro;
    }

    public void setEuro(float euro) {
        this.euro = euro;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }
}
