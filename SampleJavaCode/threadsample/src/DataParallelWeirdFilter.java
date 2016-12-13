import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class DataParallelWeirdFilter implements BufferedImageOp  {

	/**
	 * @param args
	 */
	
	int numthreads;
	static BufferedImage inputImage;
	 static BufferedImage outputImage;
	private static String X;
	static int XM,YM;
	
	public DataParallelWeirdFilter(int T){
		 numthreads = T;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*int piecesize = (int) Math.floor(111/5);
		int minx = 0;
		int maxX = piecesize;
		for(int i =0; i<5-1; i++){
			System.out.println("(" + minx + "," + maxX + "," + 0 +"," + 100 + ")");
			minx = maxX+1;
			maxX = maxX + piecesize;
		}
		System.out.println("(" + (minx) + "," + 111 + "," + 0 +"," + 100 + ")");*/
		BufferedImage input=null;
		BufferedImage output;
		BufferedImageOp filter;
       
		try {
			 X = args[0].replaceFirst("[.][^.]+$", "");
			input = ImageIO.read(new File(args[0]));
		} catch (IOException e) {
			System.out.println("Cannot read file ./image.jpg");
			System.exit(1);
		}

		output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());  

		
		
		filter = new DataParallelWeirdFilter(4);
		
		filter.filter(input,output);
		
		saveImage(output, X + "_Weird.jpg");
		
	}

	private static  void saveImage(BufferedImage image, String filename){
		try {
			ImageIO.write(image, "jpg", new File(filename));
		} catch (IOException e) {
			System.out.println("Cannot write file "+filename);
			System.exit(1);
		}
	}

	@Override
	public BufferedImage createCompatibleDestImage(BufferedImage arg0,
			ColorModel arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage filter(BufferedImage arg0, BufferedImage arg1) {
		// TODO Auto-generated method stub
		inputImage = arg0;
		outputImage = arg1;
		XM = inputImage.getWidth();
		YM = inputImage.getHeight();
		Thread[] processes = new Thread[numthreads];
		int piecesize = (int) Math.floor(inputImage.getWidth()/numthreads);
		int minx = 0;
		int maxX = piecesize;
		for(int i =0; i<numthreads-1; i++){
			processes[i] = new Subprocess(minx,maxX,0,inputImage.getHeight());
			processes[i].setName(Integer.toString(i));
			minx = maxX ;
			maxX = maxX + piecesize;
		}
		processes[numthreads-1] = new Subprocess(minx,inputImage.getWidth(),0,inputImage.getHeight());
		for(int i2 =0; i2<numthreads; i2++){
			processes[i2].start();
		}
		for(int i3 =0; i3<numthreads; i3++){
			try {
				processes[i3].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Rectangle2D getBounds2D(BufferedImage arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2D getPoint2D(Point2D arg0, Point2D arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RenderingHints getRenderingHints() {
		// TODO Auto-generated method stub
		return null;
	}

	public class Subprocess extends Thread{
		
		private int xm,X,ym,Y;
		private ArrayList<int []> Npix;
		public Subprocess(int Xmin, int Xmax, int Ymin, int Ymax){
			xm = Xmin;
			X = Xmax;
			ym = Ymin;
			Y = Ymax;
			Npix = new ArrayList<int[]>();
		}
		
		public void run(){
			process();
		}
		private synchronized void process() {
	       
	         byte[] newRGB = null;
			for (int i = xm; i < X; i++) {
				for (int j = ym; j < Y; j++) {				
					newRGB = processPixel(inputImage, i, j);
					outputImage.setRGB(i , j,RGB.bytesToInt(newRGB) );
				}
			}
		}
		private synchronized  byte[] processPixel(BufferedImage image, int x, int y) {

			byte[] finalval = new byte[3];
			finalval[0] =0;
			finalval[1] =0;
			finalval[2] =0;
			

			getneighbors(x,y);

			for(int k = 0; k<Npix.size() ; k++){

				int rgb = image.getRGB(Npix.get(k)[0],Npix.get(k)[1] );
				byte[] bytes = RGB.intToBytes(rgb);
				finalval[0] = (byte) (finalval[0] + Math.max(Math.pow(Math.E, bytes[0]), 20) + 10*Math.cos(bytes[0]));
				finalval[1] = (byte) (finalval[1] + Math.min(Math.pow(Math.E, bytes[1]), 50));
				finalval[2] = (byte) (finalval[2] + Math.min(Math.pow(Math.E, bytes[2]), 20));
			}
		try{
			finalval[0] = (byte) (finalval[0]/Npix.size());
			finalval[1] = (byte) (finalval[1]/Npix.size());
			finalval[2] = (byte) (finalval[2]/Npix.size());}
		catch(ArithmeticException e){
			System.out.println(this.getName() + "failed" + "(" + x + ","+y + ")") ;
			System.out.println("X=" + X);
			System.out.println("x=" + xm);
			System.out.println("Y=" + Y);
			System.out.println("y=" + ym);
		}
			Npix.removeAll(Npix);
			return finalval;
		}
		
		public synchronized void  getneighbors(int x, int y){
			int[] N = new int[2];
			int[] NE = new int[2];
			int[] E = new int[2];
			int[] SE = new int[2];
			int[] S = new int[2];
			int[] SW = new int[2];
			int[] W = new int[2];
			int[] NW = new int[2];
			
			N[0] = x;
			N[1] = y+1;
			NE[0] = x+1;
			NE[1] = y+1;
			E[0] = x+1;
			E[1] = y;
			SE[0] = x+1;
			SE[1] = y-1;
			S[0] = x;
			S[1] = y-1;
			SW[0] = x-1;
			SW[1] = y-1;
			W[0]= x-1;
			W[1] = y;
			NW[0] = x-1;
			NW[1] = y+1;
			if(N[0]>-1 && N[0]<XM && N[1]>-1 && N[1]<YM){
				Npix.add(N);
			}
			if(NE[0]>-1 && NE[0]<XM && NE[1]>-1 && NE[1]<YM){
				Npix.add(NE);
			}
			if(E[0]>-1 && E[0]<XM && E[1]>-1 && E[1]<YM){
				Npix.add(E);
			}
			if(SE[0]>-1 && SE[0]<XM && SE[1]>-1 && SE[1]<YM){
				Npix.add(SE);
			}
			if(S[0]>-1 && S[0]<XM && S[1]>-1 && S[1]<YM){
				Npix.add(S);
			}
			if(SW[0]>-1 && SW[0]<XM && SW[1]>-1 && SW[1]<YM){
				Npix.add(SW);
			}
			if(W[0]>-1 && W[0]<XM && W[1]>-1 && W[1]<YM){
				Npix.add(W);
			}
			if(NW[0]>-1 && NW[0]<XM && NW[1]>-1 && NW[1]<YM){
				Npix.add(NW);
			}	
		}
	}
}
