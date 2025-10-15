//import java.awt.Color;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
//
//public class ComparingImages {
//    public static BufferedImage img1 = null;
//    public static void main(String[] args) throws Exception {
//           compairImage();
//    }
//
//    public static void compairImage() throws IOException {
//        String Dir=System.getProperty("user.dir")+"\\src\\Data\\Downloads";
//        File file = new File(Dir);
//
//        BufferedImage img2;
//        // returns an array of all files
//        String[] fileList = file.list();
//        String Screenshotfile = null;
//
//        for(String str : fileList) {
//            if(str.startsWith("Screenshotter--"))
//            {
//                Screenshotfile=str;
//                System.out.println("File Name"+Screenshotfile);
//                break;
//            }
//            else
//            {
//                System.out.println("Need to Find the Screenshot");
//            }
//        }
//        if(img1==null)
//        {
//            img1 = ImageIO.read(new File(Dir+"//"+Screenshotfile));
//            File fileprocessed = new File(Dir+"//"+Screenshotfile);
//            if(fileprocessed.delete())
//            {
//                System.out.println("File deleted successfully");
//            }
//            else
//            {
//                System.out.println("Failed to delete the file");
//            }
//        }
//        else {
//
//            img2 = ImageIO.read(new File(Dir+"//"+Screenshotfile));
//            File fileprocessed = new File(Dir+"//"+Screenshotfile);
//            if(fileprocessed.delete())
//            {
//                System.out.println("File deleted successfully");
//            }
//            else
//            {
//                System.out.println("Failed to delete the file");
//            }
//            int w1 = img1.getWidth();
//            int w2 = img2.getWidth();
//            int h1 = img1.getHeight();
//            int h2 = img2.getHeight();
//
//            System.out.println("Width-"+w1+":"+w2);
//            System.out.println("Width-"+h1+":"+h2);
//            if ((w1 != w2) || (h1 != h2)) {
//                System.out.println("Both images should have same dimensions");
//            } else {
//                long diff = 0;
//                for (int j = 0; j < h1; j++) {
//                    for (int i = 0; i < w1; i++) {
//                        //Getting the RGB values of a pixel
//                        int pixel1 = img1.getRGB(i, j);
//                        Color color1 = new Color(pixel1, true);
//                        int r1 = color1.getRed();
//                        int g1 = color1.getGreen();
//                        int b1 = color1.getBlue();
//                        int pixel2 = img2.getRGB(i, j);
//                        Color color2 = new Color(pixel2, true);
//                        int r2 = color2.getRed();
//                        int g2 = color2.getGreen();
//                        int b2 = color2.getBlue();
//                        //sum of differences of RGB values of the two images
//                        long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
//                        diff = diff + data;
//                    }
//                }
//                double avg = diff / (w1 * h1 * 3);
//                double percentage = (avg / 255) * 100;
//                System.out.println("Difference: " + percentage);
//                img1=img2;
//            }
//        }
//
//    }
//
//}