import java.io.*;

import java.math.RoundingMode;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.image.*;

import javax.imageio.*;

import com.jhlabs.image.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import java.util.concurrent.locks.*;

public class ConcurrentImageProcessorTaskParallel {

	public static File[] files;
	public static File dir;
	public static long readtime = 0;
	public static long writetime = 0;
	public static long[] processtime;
	public static long totaltime = 0;
	public static String Filter;
	public static double progressvalue = 0;
	Semaphore S1 = new Semaphore(8);
	Semaphore S2 = new Semaphore(0);
	Semaphore S3 = new Semaphore(0);
	static ArrayList<BufferedImage> A = new ArrayList<BufferedImage>();
	static ArrayList<BufferedImage> D = new ArrayList<BufferedImage>();
	static ArrayList<String> DIR = new ArrayList<String>();
	static ArrayList<String> DIR2 = new ArrayList<String>();
	static ArrayList<File> F = new ArrayList<File>();
	static int numberthreads = 1;
	static int j = 0;
    static Lock LOCK = new ReentrantLock();
    static Lock LOCK2 = new ReentrantLock();
	public static void main(String[] args) throws InterruptedException {
		File [] FF;
		try{
		if(args.length==2){
		Filter = args[0];
		dir = new File(args[1]);
		FF = dir.listFiles();		
		processtime = new long[1];
		}
		else{
	
			Filter = args[1];
			dir = new File(args[2]);
			FF = dir.listFiles();
			numberthreads = Integer.parseInt(args[0]);
			processtime = new long[Integer.parseInt(args[0])];
		}
		
	
		
		for(int i =0; i<FF.length; i++){
			
		    String X = FF[i].getName().replaceFirst("[.][^.]+$", "");
			if(X.matches("image_[0-9]{2}")){
				F.add(FF[i]);
				
			}
		}
		files = new File[F.size()];
	files = F.toArray(files);
	

		ConcurrentImageProcessorTaskParallel CPT = new ConcurrentImageProcessorTaskParallel();
		}
		catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException N){
			System.out.print("enter java <#Threads &optional> <Filter> <Directory>");
		}
		

	}

	public ConcurrentImageProcessorTaskParallel() throws InterruptedException {
		Readfile R = new Readfile();
		Processfile [] P = new Processfile[numberthreads];
		Writefile WF = new Writefile();
		//Progress pro = new Progress();
	
		totaltime = System.currentTimeMillis();
		R.start();
		for(int O=0; O<numberthreads; O++){
			P[O] = new Processfile(O);
			P[O].start();
			
		}
		
		WF.start();
		//pro.start();
		R.join();
		for(int O=0; O<numberthreads; O++){
			
			P[O].join();
			
		}
		WF.join();
		//pro.join();
		totaltime = System.currentTimeMillis() - totaltime;
		DecimalFormat DF = new DecimalFormat("###.000");

		System.out.println();
		System.out.println("total readtime: " + DF.format(readtime / 1000.000)
				+ "s");
		for(int i =0; i <processtime.length;i++){
		System.out.println("total processtime: "
				+ DF.format(processtime[i] / 1000.000) + "s");
		}
		System.out.println("total writetime: "
				+ DF.format(writetime / 1000.000) + "s");
		System.out.println("total time: " + DF.format(totaltime / 1000.000)
				+ "s");

	}

	public class Readfile extends Thread {

		public void run() {
			int i = 0;
			long R = 0;
			while (i < files.length) {

				try {
					R = System.currentTimeMillis();
					A.add(ImageIO.read(files[i]));
					DIR.add(dir.getCanonicalPath() + "/"+Filter +"_"
							+ files[i].getName());
					R = System.currentTimeMillis() - R;
					readtime = readtime + R;
					S1.P();
				} catch (IOException e) {
				}
				progressvalue++;
				System.out.print("r");
				S2.V();
				i++;
			}
			for(int k = 0; k<numberthreads; k++){
			S2.V();
			}

		}
	}

	public class Processfile extends Thread {
		BufferedImage temp;
		BufferedImage O;
		BufferedImageOp Filterr;
		int T;
		
		public Processfile(int x){
			T= x;
		}
		
		long P = 0;

		public void run() {

			if (Filter.equals("oil1")) {
				
				while (true) {
                    LOCK.lock();
                    if(j>files.length-1){
  					  LOCK.unlock();
  					  break;
  				  }
                    S2.P();
                    j++;
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());					
					temp = A.get(0);
					A.remove(0);
					String name = DIR.get(0);
					DIR.remove(0);
					S1.V();
					P = System.currentTimeMillis();
			 Filterr = new OilFilter();
					((OilFilter) Filterr).setRange(1);
					LOCK.unlock();
					Filterr.filter(temp,O);
					D.add(O);
					DIR2.add(name);
					S3.V();
					P = System.currentTimeMillis() - P;
					processtime[T] = processtime[T] + P;
					progressvalue++;
					System.out.print("p");				
				}
				
			} else if (Filter.equals("weird")) {
				
	while (true) {
					LOCK.lock();					
				  if(j>files.length-1){
					  LOCK.unlock();
					  break;
				  }
					S2.P();
					j++;
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					A.remove(0);
					String name = DIR.get(0);
					DIR.remove(0);
					S1.V();		
				P = System.currentTimeMillis();	
				 Filterr = new WeirdFilter(T);
				 LOCK.unlock();
			        Filterr.filter(temp, O);		        
					D.add(O);		
					DIR2.add(name);
					S3.V();
					P = System.currentTimeMillis() - P;
					processtime[T] = processtime[T] + P;
					progressvalue++; 
					System.out.print("p");
									}			
			}
			else if (Filter.equals("oil6")) {
				
				while (true) {
                  LOCK.lock();
                  if(j>files.length-1){
					  LOCK.unlock();
					  break;
				  }
					S2.P();
					j++;
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					A.remove(0);
					String name = DIR.get(0);
					DIR.remove(0);
					S1.V();
					P = System.currentTimeMillis();
					 Filterr = new OilFilter();
					((OilFilter) Filterr).setRange(6);
					LOCK.unlock();
					Filterr.filter(temp, O);
					D.add(O);
					DIR2.add(name);
					S3.V();
					P = System.currentTimeMillis() - P;
					processtime[T] = processtime[T] + P;
					progressvalue++;
					System.out.print("p");															
				}
				
			} else if (Filter.equals("invert")) {
				
				while (true) {
                  LOCK.lock();
                  if(j>files.length-1){
					  LOCK.unlock();
					  break;
				  }
					S2.P();
					j++;
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					A.remove(0);
					String name = DIR.get(0);
					DIR.remove(0);
					S1.V();
					P = System.currentTimeMillis();
			 Filterr = new InvertFilter();
			 LOCK.unlock();
					Filterr.filter(temp, O);
					D.add(O);
					DIR2.add(name);
					S3.V();
					P = System.currentTimeMillis() - P;
					processtime[T] = processtime[T] + P;
					progressvalue++;
					System.out.print("p");
				}
				
			} else {
				while (true) {
					LOCK.lock();					
					  if(j>files.length-1){
						  LOCK.unlock();
						  break;
					  }
					S2.P();
					j++;
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					A.remove(0);
					String name = DIR.get(0);
					DIR.remove(0);
					S1.V();
					P = System.currentTimeMillis();
					Filterr = new SmearFilter();
					 ((SmearFilter) Filterr).setShape(0);
					 LOCK.unlock();
					Filterr.filter(temp, O);
					D.add(O);
					DIR2.add(name);
					S3.V();
					P = System.currentTimeMillis() - P;
					processtime[T] = processtime[T] + P;
					progressvalue++;
					System.out.print("p");
					
				}
				
			}

		}
	}

	public class Writefile extends Thread {
		public void run() {
			int L = 0;
			long W = 0;
			while (L < files.length) {
				try {
					S3.P();
					W = System.currentTimeMillis();
					ImageIO.write(D.get(0), "jpg", new File(DIR2.get(0)));
					W = System.currentTimeMillis() - W;
					writetime = writetime + W;
					System.out.print("w");
					progressvalue++;
					D.remove(0);
				
					DIR2.remove(0);
					L++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}

	public class Progress extends Thread {
		private JFrame J;
		private JProgressBar JPB;

		public Progress() {
			J = new JFrame("Image processing");
			J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPB = new JProgressBar();
			JPB.setValue((int) progressvalue);
			JPB.setStringPainted(true);
			J.add(JPB);
			J.setSize(500, 100);
			J.setVisible(true);
		}

		public void run() {
			double progresspercent = 0;
			int maxvalue = files.length * 3;
			while (progressvalue < maxvalue) {
				progresspercent = (progressvalue / maxvalue) * 100;
				JPB.setValue((int) progresspercent);
				JPB.setStringPainted(true);
			}
			progresspercent = (progressvalue / maxvalue) * 100;
			JPB.setValue((int) progresspercent);
			JPB.setStringPainted(true);
		}
	}
}
