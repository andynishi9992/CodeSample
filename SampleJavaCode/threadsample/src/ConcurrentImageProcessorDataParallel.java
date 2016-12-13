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

public class ConcurrentImageProcessorDataParallel {

	public static File[] files;
	public static File dir;
	public static long readtime = 0;
	public static long writetime = 0;
	public static long processtime = 0;
	public static long totaltime = 0;
	public static String Filter;
	public static double progressvalue = 0;
	Semaphore S1 = new Semaphore(8);
	Semaphore S2 = new Semaphore(0);
	Semaphore S3 = new Semaphore(0);
	static ArrayList<BufferedImage> A = new ArrayList<BufferedImage>();
	static ArrayList<BufferedImage> D = new ArrayList<BufferedImage>();
	static ArrayList<String> DIR = new ArrayList<String>();
	static ArrayList<BufferedImageOp> C = new ArrayList<BufferedImageOp>();
	static ArrayList<File> F = new ArrayList<File>();
	static int DataThreads =1;

	public static void main(String[] args) throws InterruptedException {
		File [] FF;
		try{
		if(args.length ==2){
		
			Filter = args[0];
			dir = new File(args[1]);
			FF = dir.listFiles();
			DataThreads = 1;
		}
		else{
		
			Filter = args[1];
			dir = new File(args[2]);
			FF = dir.listFiles();
			DataThreads = Integer.parseInt(args[0]);
		}
		for(int i =0; i<FF.length; i++){
			
		    String X = FF[i].getName().replaceFirst("[.][^.]+$", "");
			if(X.matches("image_[0-9]{2}")){
				F.add(FF[i]);
				
			}
		}
		files = new File[F.size()];
	files = F.toArray(files);
	

		if (Filter.equals("oil1")) {
			for (int k = 0; k < files.length; k++) {
				try {
					DIR.add(dir.getCanonicalPath() + "/oil1_"
							+ files[k].getName());
				} catch (IOException e) {

				}
			}
			ConcurrentImageProcessorDataParallel CIP = new ConcurrentImageProcessorDataParallel();
		} else if (Filter.equals("oil6")) {
			for (int k = 0; k < files.length; k++) {
				try {
					DIR.add(dir.getCanonicalPath() + "/oil6_"
							+ files[k].getName());
				} catch (IOException e) {

				}
			}
			ConcurrentImageProcessorDataParallel CIP = new ConcurrentImageProcessorDataParallel();
		}

		else if (Filter.equals("smear")) {
			for (int k = 0; k < files.length; k++) {
				try {
					DIR.add(dir.getCanonicalPath() + "/smear_"
							+ files[k].getName());
				} catch (IOException e) {

				}
			}
			ConcurrentImageProcessorDataParallel CIP = new ConcurrentImageProcessorDataParallel();
		} else if (Filter.equals("invert")) {
			for (int k = 0; k < files.length; k++) {
				try {
					DIR.add(dir.getCanonicalPath() + "/invert_"
							+ files[k].getName());
				} catch (IOException e) {

				}

			}
		}
			else if (Filter.equals("weird")) {
				for (int k = 0; k < files.length; k++) {
					try {
						DIR.add(dir.getCanonicalPath() + "/weird_"
								+ files[k].getName());
					} catch (IOException e) {

					}
				}
			ConcurrentImageProcessorDataParallel CIP = new ConcurrentImageProcessorDataParallel();
		} else {

			System.out.println("your option is invalid");
		}
		}
		catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException N){
			System.out.print("enter java <#Dataparallel Threads &optional> <Filter> <Directory>");
		}
	}

	public ConcurrentImageProcessorDataParallel() throws InterruptedException {
		Readfile R = new Readfile();
		Processfile P = new Processfile();
		Writefile WF = new Writefile();
		//Progress pro = new Progress();
		totaltime = System.currentTimeMillis();
		R.start();
		P.start();
		WF.start();
		//pro.start();
		R.join();
		P.join();
		WF.join();
		//pro.join();
		totaltime = System.currentTimeMillis() - totaltime;
		DecimalFormat DF = new DecimalFormat("###.000");

		System.out.println();
		System.out.println("total readtime: " + DF.format(readtime / 1000.000)
				+ "s");
		System.out.println("total processtime: "
				+ DF.format(processtime / 1000.000) + "s");
		System.out.println("total writetime: "
				+ DF.format(writetime / 1000.000) + "s");
		System.out.println("total time: " + DF.format(totaltime / 1000.000)
				+ "s");

	}

	public class Readfile extends Thread {

		public void run() {
			int i = 0;
			int s = 0;
			long R = 0;
			while (i < files.length) {

				try {
					R = System.currentTimeMillis();
					A.add(ImageIO.read(files[i]));
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
			S2.V();

		}
	}

	public class Processfile extends Thread {
		BufferedImage temp;
		BufferedImage O;
		int j = 0;
		long P = 0;

		public void run() {

			if (Filter.equals("oil1")) {
				S2.P();
				while (j < files.length) {

					S2.P();
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					P = System.currentTimeMillis();
					C.add(new OilFilter());
					((OilFilter) C.get(C.size() - 1)).setRange(1);
					C.get(C.size() - 1).filter(temp, O);
					D.add(O);
					P = System.currentTimeMillis() - P;
					processtime = processtime + P;
					progressvalue++;
					System.out.print("p");
					A.remove(0);
					j++;
					S3.V();
					S1.V();
				}
				S3.V();
			} else if (Filter.equals("oil6")) {
				S2.P();
				while (j < files.length) {

					S2.P();
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					P = System.currentTimeMillis();
					C.add(new OilFilter());
					((OilFilter) C.get(C.size() - 1)).setRange(6);
					C.get(C.size() - 1).filter(temp, O);
					D.add(O);
					P = System.currentTimeMillis() - P;
					processtime = processtime + P;
					progressvalue++;
					System.out.print("p");
					A.remove(0);
					j++;
					S3.V();
					S1.V();
				}
				S3.V();
			} else if (Filter.equals("weird")) {
				S2.P();
				while (j < files.length) {

					S2.P();
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					P = System.currentTimeMillis();
					C.add(new DataParallelWeirdFilter(DataThreads));
					C.get(C.size() - 1).filter(temp, O);
					D.add(O);
					P = System.currentTimeMillis() - P;
					processtime = processtime + P;
					progressvalue++;
					System.out.print("p");
					A.remove(0);
					j++;
					S3.V();
					S1.V();
				}
				S3.V();
			} 
			else if (Filter.equals("invert")) {
				S2.P();
				while (j < files.length) {

					S2.P();
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					P = System.currentTimeMillis();
					C.add(new InvertFilter());

					C.get(C.size() - 1).filter(temp, O);
					D.add(O);
					P = System.currentTimeMillis() - P;
					processtime = processtime + P;
					progressvalue++;
					System.out.print("p");
					A.remove(0);
					j++;
					S3.V();
					S1.V();
				}
				S3.V();
			} else {
				S2.P();
				while (j < files.length) {

					S2.P();
					O = new BufferedImage(A.get(0).getWidth(), A.get(0)
							.getHeight(), A.get(0).getType());
					temp = A.get(0);
					P = System.currentTimeMillis();
					C.add(new SmearFilter());
					((SmearFilter) C.get(C.size() - 1)).setShape(0);
					C.get(C.size() - 1).filter(temp, O);
					D.add(O);
					P = System.currentTimeMillis() - P;
					processtime = processtime + P;
					progressvalue++;
					System.out.print("p");
					A.remove(0);
					j++;
					S3.V();
					S1.V();
				}
				S3.V();
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
					ImageIO.write(D.get(0), "jpg", new File(DIR.get(0)));
					W = System.currentTimeMillis() - W;
					writetime = writetime + W;
					System.out.print("w");
					progressvalue++;
					D.remove(0);
					C.remove(0);
					DIR.remove(0);
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
