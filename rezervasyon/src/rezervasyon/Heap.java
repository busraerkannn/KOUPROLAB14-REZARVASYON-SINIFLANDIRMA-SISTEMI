/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezervasyon;

import java.util.ArrayList;
import java.util.List;


public class Heap {

   int dugumSayisi=10;// number of nodes in tree
   private int currentIndex=0;
   int index = 0;
   // heap format
   private int nBlanks = 32;
   private int itemsPerRow = 1;
   private int column = 0;
   class HeapNode
   {
        int rSayi;
        HeapNode rightChildren;
        HeapNode leftChildren;
        HeapNode parent;
   } 
   
    HeapNode heapRoot = null;
    List<HeapNode> rootNodeList = new ArrayList<HeapNode>();
    public HeapNode HeapTreeOlustur(ArrayList<Integer> rSayiArray) {
        
        heapRoot = new HeapNode();
        heapRoot.parent = null;
        heapRoot.rSayi = rSayiArray.get(index++);
        HeapTreeOlusturRecursive(rSayiArray, heapRoot);
        while(!rootNodeList.isEmpty()){
            HeapTreeOlusturRecursive(rSayiArray, rootNodeList.get(0));
        }
        index = 0;      
       //ağaç max heap e göre tekrar düzenlenmeli
        return heapRoot;
    }
    
    public void HeapTreeOlusturRecursive(ArrayList<Integer> rCountArray, HeapNode node){
        if(index < rCountArray.size()){
            
            HeapNode newNodeLeft = new HeapNode();
            newNodeLeft.rSayi = rCountArray.get(index++);
            newNodeLeft.parent = node;
            node.leftChildren = newNodeLeft;
            rootNodeList.add(newNodeLeft);
            kontrolMaxHeap(newNodeLeft); 
            
            if (index < rCountArray.size()) {
                 HeapNode newNodeRight = new HeapNode();
                 newNodeRight.rSayi = rCountArray.get(index++);
                 newNodeRight.parent = node;
                 node.rightChildren = newNodeRight;
                 rootNodeList.add(newNodeRight);
                 kontrolMaxHeap(newNodeRight); 
            }
        }
        rootNodeList.remove(node);
    }
    
    public void gosterHeapTree(HeapNode root){
                
        display(root.rSayi + "");
        if (root.leftChildren != null) {
             display(root.leftChildren.rSayi + "");
        }
        if (root.rightChildren != null) {
            display(root.rightChildren.rSayi + "");
        }
        treeNode(root.leftChildren, root.rightChildren);
    }
    
    public void kontrolMaxHeap(HeapNode node){
            int temp;
            
            if(node.parent != null && node.rSayi > node.parent.rSayi){
                //eklenen son yaprak sol tarafa eklenmişse ve sağ yaprak boşsa
                temp = node.rSayi;
                node.rSayi = node.parent.rSayi;
                node.parent.rSayi = temp;
                kontrolMaxHeap(node.parent);
            }
    }
    public void treeNode(HeapNode leftNode, HeapNode rightNode){
        if (leftNode != null ) {
            if (leftNode.leftChildren != null) {
                 display(leftNode.leftChildren.rSayi + "");
            }
            if(leftNode.rightChildren != null){
                display(leftNode.rightChildren.rSayi + "");
            } 
        }
        else {
              System.out.println("");
          return;
        }
        if (rightNode != null) {
            if (rightNode.leftChildren != null) {
                 display(rightNode.leftChildren.rSayi + "");
            }
            if (rightNode.rightChildren != null) {
                 display(rightNode.rightChildren.rSayi + "");
            }
        }
        else{
              System.out.println("");
          return;
        }
        treeNode(leftNode.leftChildren, leftNode.rightChildren);
        treeNode(rightNode.leftChildren, rightNode.rightChildren);
    }
    
    
    public void display(String heapPrintItem){ //Bu kod kaynakçada belirtilen siteden alınmıştır.
            
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(heapPrintItem);

         if(++currentIndex == dugumSayisi)           // done?
            return;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
            // end for
    }
    
}