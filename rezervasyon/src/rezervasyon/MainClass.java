/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezervasyon;

import java.io.IOException;
import java.util.Scanner;
import rezervasyon.Rezervasyon.Node;


public class MainClass {
     public static void main(String[] args) throws IOException {
         Rezervasyon r = new Rezervasyon();
         r.dosyadanOku();
         RezervasyonBilgileri x=new RezervasyonBilgileri();
         Node k=null;
        Scanner sc=new Scanner(System.in);
        boolean devam=true;
        while(devam)
        {
            System.out.println("1:Kategori bulma işlemi");
            System.out.println("2:Yeni kategori ekleme");
            System.out.println("3:Kategori Silme");
            System.out.println("4:Kullanıcı ekleme");
            System.out.println("5:Kullanıcı silme");
            System.out.println("6:Kullanıcıya göre kategori listeleme");
            System.out.println("7:Kategoriye göre kullanıcı listeleme");
            System.out.println("8:Rezervasyon yerine göre kullanıcı listeleme");
            System.out.println("9:Kullanıcıya göre rezervasyon listeleme");
            System.out.println("Seçim yapınız");
            int secim=sc.nextInt();
            sc.nextLine();
            switch(secim)
            {
                case 1:
                    System.out.println("Bulmak istediğiniz kategori nedir?");
                    String kategori=sc.nextLine();
                    r.search(kategori);
                    break;
                case 2:
                    System.out.println("Hangi kategorinin altına ekleme yapmak istiyorsunuz?");
                    String kategori2=sc.nextLine();
                    System.out.println("Eklenecek kategorinin adı nedir?");
                    String altkategori=sc.nextLine();
                    r.kategoriEkle(kategori2, altkategori);
                    break;
                case 3:
                    System.out.println("Başka seçim yapınız.");
                    break;
                case 4:
                    System.out.println("Kullanıcının ekleneceği kategori nedir?");
                    String kategori1=sc.nextLine();
                    System.out.println("Kullanıcının ID'si nedir?");
                    String userID=sc.nextLine();
                    r.kullaniciEkle(kategori1,userID);
                    break;
                case 5:
                    System.out.println("Başka seçim yapınız.");
                    break;
                case 6:
                    String kullanici;
                    System.out.print("Kullanici girin: ");
                    kullanici=sc.nextLine();
                    r.listele1(kullanici);
                    break;
                case 7:
                    String kategori3;
                    System.out.print("Kategori girin: ");
                    kategori3=sc.nextLine();
                    r.listele2(kategori3);
                    break;
                case 8:
                    String yer1;
                    System.out.print("Rezervasyon yeri girin: ");
                    yer1=sc.nextLine();
                    r.listele3(yer1);
                    break;
                case 9:
                    String kullanici1;
                    System.out.print("Kullanici girin: ");
                    kullanici1=sc.nextLine();
                    r.listele4(kullanici1);
                    break;
                                  
            }
        }
         

    } 
}