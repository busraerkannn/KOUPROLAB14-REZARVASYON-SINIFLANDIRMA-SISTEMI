/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezervasyon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import rezervasyon.Heap.HeapNode;
//import rezervasyon.Heap.HeapNode;


public  class Rezervasyon {
    ArrayList<RezervasyonBilgileri> rlist = new ArrayList<RezervasyonBilgileri>();
    ArrayList<RezervasyonGrup> rgList = new ArrayList<RezervasyonGrup>();
    //private Node root;
    class Node {
    String etiket;
    int derinlik;
    List<Node> rightChildren;
    List<Node> leftChildren;
    HeapNode rezervasyonNode;
    Node parent;
        public Node(){
            derinlik=0;
            rightChildren = new ArrayList<Node>();
            leftChildren = new ArrayList<Node>();
        }
    }
    
    class Tree{
    private Node root;
    public Tree() // constructor
    {
        
        root=null;
    } 

    public void agacOlustur() throws IOException{
        root=new Node();
        root.etiket = "rezervasyon";
        root.derinlik++;
        
        for (RezervasyonBilgileri x : rlist) {
            boolean kontrol = false;
            if (x.kategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : root.leftChildren) {
                       //ağacın sol düğümlerinde böyle bir kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(x.kategori)) {
                           kontrol = true;
                           if (x.altkategori != null && !x.altkategori.isEmpty()) {
                               altKategoriEkle(child, x.altkategori);
                           }
                    }
                  } 

                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.etiket = x.kategori;
                     leftNode.parent = root;
                     root.leftChildren.add(leftNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle(leftNode, x.altkategori);
                     }
                }
            }
            else{
                    for (Node child : root.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(x.kategori)) {
                        kontrol = true;
                        if (x.altkategori != null && !x.altkategori.isEmpty()) {
                               altKategoriEkle(child, x.altkategori);
                        }
                        
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket = x.kategori;
                     righttNode.parent = root;
                     root.rightChildren.add(righttNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle(righttNode, x.altkategori);
                     }
                }
            }
        } 
    }
    
    public void altKategoriEkle(Node currentNode, String altKategori){
        boolean kontrol = false;

        
        if (altKategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : currentNode.leftChildren) {
                       //ağacın sol düğümlerinde böyle bir kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(altKategori)) {
                           kontrol = true;
                    }
                  } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.etiket = altKategori;
                     leftNode.parent = currentNode;
                     currentNode.leftChildren.add(leftNode);
                     kategoriRezervasyon(leftNode);
                }
            }
            else{
                    for (Node child : currentNode.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(altKategori)) {
                        kontrol = true;
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket = altKategori;
                     righttNode.parent = currentNode;
                     currentNode.rightChildren.add(righttNode);
                     kategoriRezervasyon(righttNode);
                }
            }
    }
    
    public void kategoriRezervasyon(Node currentNode){
       ArrayList<Integer> rezervasyonSayilari = new ArrayList<>();
       for(RezervasyonGrup grup : rgList){
           if(grup.altKategori!=null){
           if(grup.altKategori.equals(currentNode.etiket)  && grup.kategori.equals(currentNode.parent.etiket)){
               rezervasyonSayilari.add(grup.rezervasyonSayısı);
           }
           }
       }
       Heap heap = new Heap();
       heap.dugumSayisi=rezervasyonSayilari.size();
       currentNode.rezervasyonNode = heap.HeapTreeOlustur(rezervasyonSayilari);
       System.out.println(currentNode.etiket);
       heap.gosterHeapTree(currentNode.rezervasyonNode);
    }
    public void kategoriRezervasyon2(Node currentNode){
       ArrayList<Integer> rezervasyonSayilari = new ArrayList<>();
      for(RezervasyonGrup grup : rgList){
           if(grup.kategori.equals(currentNode.etiket)){
               rezervasyonSayilari.add(grup.rezervasyonSayısı);
           }
       }
       Heap heap = new Heap();
       heap.dugumSayisi=rezervasyonSayilari.size();
       currentNode.rezervasyonNode = heap.HeapTreeOlustur(rezervasyonSayilari);
       System.out.println(currentNode.etiket);
       heap.gosterHeapTree(currentNode.rezervasyonNode);
    }
    public void AgacYazdir(){
        String printStr = "";
        for (Node child : root.leftChildren) {
            for (Node subchild : child.leftChildren) {
                if (subchild != null) {
                    printStr += subchild.etiket + ",";
                }
            }
            for (Node subchild : child.rightChildren) {
                if (subchild != null) {
                    printStr += subchild.etiket + ",";
                }
            }
            System.out.println(child.etiket + " : " + printStr);
            printStr = "";
        }
        
        for (Node child : root.rightChildren) {
            for (Node subchild : child.leftChildren) {
                if (subchild != null) {
                    printStr += subchild.etiket + ",";
                }
            }
            for (Node subchild : child.rightChildren) {
                if (subchild != null) {
                    printStr += subchild.etiket + ",";
                }
            }
            System.out.println(child.etiket + " : " + printStr);
            printStr = "";
        }
    }
    public void search(Node root,String kategori)
    {

        Scanner sc=new Scanner(System.in);
        RezervasyonBilgileri x=new RezervasyonBilgileri();
            if(kategori.toUpperCase().charAt(0) < 'M'){
                for (Node child : root.leftChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(kategori)) {
                           System.out.println(kategori+" kategorisi bulundu");
                           System.out.println("1:Yeni kaegori ekleme");
                           System.out.println("2:Kategori silme");
                           int secim=sc.nextInt();
                           sc.nextLine();
                           if(secim==1)
                           {
                               System.out.println("Altına eklenecek kategorinin ismi:");
                               x.altkategori=sc.nextLine();
                               x.kategori=kategori;
                               kategoriEkle(x);
                               AgacYazdir();
                           }
                           else
                           {
                               
                           }
                           return; 
                       }
                }
            }    
            else if(kategori.toUpperCase().charAt(0) >= 'M')
            {
                for (Node child : root.rightChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(kategori)) {
                           System.out.println(kategori+" kategorisi bulundu");
                           System.out.println("1:Yeni kaegori ekleme");
                           System.out.println("2:Kategori silme");
                           int secim=sc.nextInt();
                           sc.nextLine();
                           if(secim==1)
                           {
                               System.out.println("Altına eklenecek kategorinin ismi:");
                               x.altkategori=sc.nextLine();
                               x.kategori=kategori;
                               kategoriEkle(x);
                               AgacYazdir();
                           }
                           else
                           {
                               
                           }
                           return; 
                       }
                }
            }
            
        
       
    }
    public void search2(Node root,String kategori,String userID)
    {
        RezervasyonBilgileri k1=new RezervasyonBilgileri();
        RezervasyonGrup k2=new RezervasyonGrup();
        Scanner sc=new Scanner(System.in);
        for (RezervasyonBilgileri x : rlist){
        if(kategori.toUpperCase().charAt(0) < 'M'){
                for (Node child : root.leftChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(kategori)) {
                           if(x.kullanıcıId.equals(userID))
                           {
                               System.out.println("Aynı kullanıcı Id'si mevcuttur.İşlem iptal edilmiştir");
                               return;
                           }
                           else
                           {
                               System.out.println("Yer ID'sini giriniz:");
                               k1.yerId=sc.nextLine();
                               System.out.println("Zamanı giriniz:");
                               k1.zaman=sc.nextLine();
                               System.out.println("Enlemi giriniz:");
                               k1.enlem=sc.nextLine();
                               System.out.println("Boylamı giriniz:");
                               k1.boylam=sc.nextLine();
                               System.out.println("Şehri giriniz:");
                               k1.sehir=sc.nextLine();
                               k1.kategori=kategori;
                               k2.kategori=kategori;
                               k1.kullanıcıId=userID;
                               k2.kullanıcıId=userID;
                               k2.rezervasyonSayısı=1;
                               rgList.add(k2);
                               kategoriEkle2(k1);
                               
                           }
                       }
                }
        }
         if(kategori.toUpperCase().charAt(0) >= 'M'){
                for (Node child : root.rightChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(kategori)) {
                           if(x.kullanıcıId.equals(userID))
                           {
                               System.out.println("Aynı kullanıcı Id'si mevcuttur.İşlem iptal edilmiştir");
                               return;
                           }
                           else
                           {
                               System.out.println("Yer ID'sini giriniz:");
                               k1.yerId=sc.nextLine();
                               System.out.println("Zamanı giriniz:");
                               k1.zaman=sc.nextLine();
                               System.out.println("Enlemi giriniz:");
                               k1.enlem=sc.nextLine();
                               System.out.println("Boylamı giriniz:");
                               k1.boylam=sc.nextLine();
                               System.out.println("Şehri giriniz:");
                               k1.sehir=sc.nextLine();
                               k1.kategori=kategori;
                               k2.kategori=kategori;
                               k1.kullanıcıId=userID;
                               k2.kullanıcıId=userID;
                               k2.rezervasyonSayısı=1;
                               rgList.add(k2);
                               kategoriEkle2(k1);
                               
                           }
                       }
                }
        }
        }
    }
    public void kategoriEkle(RezervasyonBilgileri x){
            boolean kontrol = false;
            if (x.kategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : root.leftChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(x.kategori)) {
                           kontrol = true;
                           if (x.altkategori != null && !x.altkategori.isEmpty()) {
                               altKategoriEkle1(child,x.altkategori);
                               
                           }
                       
                    }
                  } 

                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.etiket =x.kategori;
                     leftNode.parent = root;
                     root.leftChildren.add(leftNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle1(leftNode,x.altkategori);
                     }
                }
            }
            else{
                    for (Node child : root.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(x.kategori)) {
                        kontrol = true;
                        if (x.altkategori != null && !x.altkategori.isEmpty()) {
                               altKategoriEkle1(child,x.altkategori);
                        }
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket =x.kategori;
                     righttNode.parent = root;
                     root.rightChildren.add(righttNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle1(righttNode,x.altkategori);
                     }
                }
            }
         
    }
    
    public void altKategoriEkle1(Node currentNode, String altKategori){
        boolean kontrol = false;

        
        if (altKategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : currentNode.leftChildren) {
                       //ağacın sol düğümlerinde böyle bir kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(altKategori)) {
                           kontrol = true;
                    }
                  } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.etiket = altKategori;
                     leftNode.parent = currentNode;
                     currentNode.leftChildren.add(leftNode);
                }
            }
            else{
                    for (Node child : currentNode.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(altKategori)) {
                        kontrol = true;
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket = altKategori;
                     righttNode.parent = currentNode;
                     currentNode.rightChildren.add(righttNode);
            
                }
            }
    }
    public void kategoriEkle2(RezervasyonBilgileri x){
            boolean kontrol = false;
            if (x.kategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : root.leftChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(x.kategori)) {
                           kontrol = true;
                           if(x.kullanıcıId != null && !x.kullanıcıId.isEmpty())
                           {
                                Node leftNode = new Node();
                                leftNode.etiket =x.kategori;
                                
                               kategoriRezervasyon2(leftNode);
                               
                           }
                    }
                  } 

                /* if (!isDefined) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.label =x.kategori;
                     leftNode.parent = root;
                     root.leftChildren.add(leftNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle1(leftNode,x.altkategori);
                     }
                }*/
            }
            else{
                    for (Node child : root.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(x.kategori)) {
                        kontrol = true;
                        if(x.kullanıcıId != null && !x.kullanıcıId.isEmpty())
                           {
                                Node rightNode = new Node();
                                rightNode.etiket =x.kategori;
                                
                               kategoriRezervasyon2(rightNode);
                               
                           }
                    }
                 } 
                
        /*         if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket =x.kategori;
                     righttNode.parent = root;
                     root.rightChildren.add(righttNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle1(righttNode,x.altkategori);
                     }
                }*/
            }
         
    }
    
    public void altKategoriEkle2(Node currentNode, String altKategori){
        boolean kontrol = false;

        
        if (altKategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : currentNode.leftChildren) {
                       //ağacın sol düğümlerinde böyle bir kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(altKategori)) {
                           kontrol = true;
                    }
                  } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.etiket = altKategori;
                     leftNode.parent = currentNode;
                     currentNode.leftChildren.add(leftNode);
                }
            }
            else{
                    for (Node child : currentNode.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(altKategori)) {
                        kontrol = true;
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket = altKategori;
                     righttNode.parent = currentNode;
                     currentNode.rightChildren.add(righttNode);
                }
            }
    }
     public void kategoriEkle3(RezervasyonBilgileri x){
 
            boolean kontrol = false;
            if (x.kategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : root.leftChildren) {
                       //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(x.kategori)) {
                           kontrol = true;
                           if (x.altkategori != null && !x.altkategori.isEmpty()) {
                               altKategoriEkle3(child,x.altkategori);
                               
                           }
                           
                    }
                  } 

                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     leftNode.etiket =x.kategori;
                     leftNode.parent = root;
                     root.leftChildren.add(leftNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle3(leftNode,x.altkategori);
                     }
                }
            }
            else{
                    for (Node child : root.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(x.kategori)) {
                        kontrol = true;
                        if (x.altkategori != null && !x.altkategori.isEmpty()) {
                               altKategoriEkle3(child,x.altkategori);
                        }
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket =x.kategori;
                     righttNode.parent = root;
                     root.rightChildren.add(righttNode);
                     if (x.altkategori != null && !x.altkategori.isEmpty()) {
                        altKategoriEkle3(righttNode,x.altkategori);
                     }
                }
            }
        //} 
    }
    
    public void altKategoriEkle3(Node currentNode, String altKategori){
        boolean kontrol = false;

        
        if (altKategori.toUpperCase().charAt(0) < 'M') {
                    for (Node child : currentNode.leftChildren) {
                       //ağacın sol düğümlerinde böyle bir kategori var mı kontrol  ediliyor 
                       if (child.etiket.equals(altKategori)) {
                           kontrol = true;
                           System.out.println("Aynı isme sahip bir kategori var.İşlem iptal edilmiştir.");
                           return;
                    }
                  } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node leftNode = new Node();
                     
                     leftNode.etiket = altKategori;
                     leftNode.parent = currentNode;
                     
                     currentNode.leftChildren.add(leftNode);
                }
            }
            else{
                    for (Node child : currentNode.rightChildren) {
                    //ağacın sol düğümlerinde böyle bşr kategori var mı kontrol  ediliyor 
                    if (child.etiket.equals(altKategori)) {
                        kontrol = true;
                    }
                 } 
                
                 if (!kontrol) {
                    //kategori  tanımlı değil. Yeni kategori ekleniyor.
                     Node righttNode = new Node();
                     righttNode.etiket = altKategori;
                     righttNode.parent = currentNode;
                     currentNode.rightChildren.add(righttNode);
                }
            }
    }
}
    public void dosyadanOku() throws IOException{
        
        File file = new File("rezervasyon.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        
        boolean kontrol = false;
        String satir = reader.readLine();
 
            while (satir!=null) {
                satir = reader.readLine();
                if (satir!= null) {
                    String[] parts = satir.split(",");
                    RezervasyonBilgileri bilgi = new RezervasyonBilgileri();
                    bilgi.kullanıcıId = parts[0];
                    bilgi.yerId = parts[1];
                    bilgi.zaman = parts[2];
                    bilgi.enlem = parts[3];
                    bilgi.boylam = parts[4];
                    bilgi.sehir = parts[5];
                    
                    String[] partsKategori = parts[6].split(":");
                    bilgi.kategori = partsKategori[0];
                    bilgi.altkategori = partsKategori[1];
                    rlist.add(bilgi);
                    
                    for(RezervasyonGrup grup : rgList){
                        if (grup.altKategori.equals(bilgi.altkategori) && grup.kategori.equals(bilgi.kategori) && grup.kullanıcıId.equals(bilgi.kullanıcıId)) {
                            kontrol = true;
                            grup.rezervasyonSayısı += 1;
                            break;
                        }
                    }
                    if (!kontrol) {
                        RezervasyonGrup yeniGrup = new RezervasyonGrup();
                        yeniGrup.kategori = bilgi.kategori;
                        yeniGrup.altKategori = bilgi.altkategori;
                        yeniGrup.kullanıcıId = bilgi.kullanıcıId;
                        yeniGrup.rezervasyonSayısı = 1;
                        rgList.add(yeniGrup);
                    }
                    kontrol = false;
                }
            }
            Tree tree = new Tree();
            tree.agacOlustur();
            tree.AgacYazdir();

    }
    public void search(String kategori) throws IOException
    {
        Tree t1=new Tree();
        t1.agacOlustur();
        t1.search(t1.root,kategori);
        
    }
    public void kullaniciEkle(String kategori,String userID) throws IOException
    {
        Tree t2=new Tree();
        t2.agacOlustur();
        t2.search2(t2.root,kategori,userID);
        
    }
    public void kategoriEkle(String kategori,String altkategori) throws IOException
    {
        Tree t3=new Tree();
        t3.agacOlustur();
        RezervasyonBilgileri x=new RezervasyonBilgileri();
        x.kategori=kategori;
        x.altkategori=altkategori;
        t3.kategoriEkle3(x);
        t3.AgacYazdir();
        
    }
    public void listele1(String kullanici)
    {
        String[] str=new String[1000];
        
        int i=0;
        int j=0;
        int s=0;
        
        for (RezervasyonBilgileri x : rlist) {
                if(x.kullanıcıId.equals(kullanici))
                {
                        if(str[0]==null)
                        {
                            System.out.println(x.altkategori);
                            str[i]=x.altkategori;
                            i++;
                        }
                        else
                        {
                           for(j=0;j<str.length;j++)
                           {
                               if((x.altkategori).equals(str[j]))
                               {
                                   s=1;
                                   break;
                               }
                           }
                           if(s==0)
                           {
                               System.out.println(x.altkategori);
                                str[i]=x.altkategori;
                                i++;
                           }
                           s=0;
                        }
                    
                }

            }
            
        
    }
    public void listele2(String kategori1)
    {
        String[] str=new String[1000];
        
        int i=0;
        int j=0;
        int s=0;
        for (RezervasyonBilgileri x : rlist) {
                if(x.kategori.equals(kategori1))
                {
                    if(str[0]==null)
                        {
                            System.out.println(x.kullanıcıId);
                            str[i]=x.kullanıcıId;
                            i++;
                        }
                        else
                        {
                           for(j=0;j<str.length;j++)
                           {
                               if((x.kullanıcıId).equals(str[j]))
                               {
                                   s=1;
                                   break;
                               }
                           }
                           if(s==0)
                           {
                               System.out.println(x.kullanıcıId);
                                str[i]=x.kullanıcıId;
                                i++;
                           }
                           s=0;
                        }
                }
            }
        
        
    }
    public void listele3(String yer1)
    {
        String[] str=new String[1000];
        
        int i=0;
        int j=0;
        int s=0;
        for(RezervasyonBilgileri x : rlist){
                if(x.yerId.equals(yer1))
                {
                    if(str[0]==null)
                        {
                            System.out.println(x.kullanıcıId);
                            str[i]=x.kullanıcıId;
                            i++;
                        }
                        else
                        {
                           for(j=0;j<str.length;j++)
                           {
                               if((x.kullanıcıId).equals(str[j]))
                               {
                                   s=1;
                                   break;
                               }
                           }
                           if(s==0)
                           {
                               System.out.println(x.kullanıcıId);
                                str[i]=x.kullanıcıId;
                                i++;
                           }
                           s=0;
                        }
                }
            }
    }
    public void listele4(String kullanici1)
    {
        String[] str=new String[1000];
        
        int i=0;
        int j=0;
        int s=0;
        for(RezervasyonBilgileri x : rlist){
                if(x.kullanıcıId.equals(kullanici1))
                {
                    System.out.println(x.yerId +" , "+x.enlem+" , "+x.boylam+" , "+x.sehir+" , "+x.zaman);
                    
                }
            }
    }
}