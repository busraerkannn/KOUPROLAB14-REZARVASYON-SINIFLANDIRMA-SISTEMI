package rezervasyon;

public class RezervasyonBilgileri {
    public String kullanıcıId;
    public String yerId;
    public String zaman;
    public String enlem;
    public String boylam;
    public String sehir;
    public String kategori;
    public String altkategori;
    public int derinlik=0;
    public String kategoriYolu;

}

class RezervasyonGrup{
    public String kullanıcıId;
    public String kategori;
    public String altKategori;
    public int rezervasyonSayısı;
}