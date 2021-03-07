package model.bmp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/*
    8-bit color and 16-bit color images
    - The bits refer to the number of possible tonal values
      available to each color channel (red, green, and blue) of each pixel.

    32-bit image
    - Includes an 8-bit Alpha channel,
      where Alpha denotes transparency,
      zero being invisible, and 255 being fully opaque.
    - Alpha is a measure of overall pixel intensity.


    Index values :
    BLUE : [0, 7] : MSB is 0 ; LSB is 7
    GREEN : [8, 15] : MSB is 8 ; LSB is 15
    RED : [16, 23] : MSB is 16 ; LSB is 23

    If present :
    Alpha channel : [0, 7] : MSB is 0 ; ! R G B planes shifted by 8 bits !
 */

public class BitPlaneSlicing {

    private BufferedImage originalImage;
    private BufferedImage transformedImage;
    private int currentTransition;
    // The number of transformations supported
    private static final int MAX_TRANSFORMATIONS = 33;

    private int pixelSize;

    /*
        Consider a color pixel with the following values:

        A = 255 ; R = 100 ; G = 150 ; B = 200

        ARGB have an integer value in the <0 ; 255>

       Convert the color pixel into negative:
       - Subtract the value of R, G and B from 255

        R = 255 - 100 = 155
        G = 255 - 150 = 105
        B = 255 - 200 = 55

        The new ARGB value will be:

        A = 255 ; R = 155 ; G = 105 ; B = 55

        We don't have to change the alpha value
        because it only controls the transparency of the pixel
     */
    private void colourInversion() {

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        transformedImage = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_ARGB);

        int currentPixelColour = 0;

        for (int indexWidth = 0; indexWidth < originalImage.getWidth(); indexWidth++) {
            for (int indexHeight = 0; indexHeight < originalImage.getHeight(); indexHeight++) {

                currentPixelColour = originalImage.getRGB(indexWidth, indexHeight);

                Color color = new Color(currentPixelColour);
                color = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());

                transformedImage.setRGB(indexWidth, indexHeight, color.getRGB());
            }
        }
    }



    //    ----------------------------------------------------------------------------------------

    private void singlePlane(int d) {

        transformedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<originalImage.getWidth();i++)
            for(int j=0;j<originalImage.getHeight();j++)
            {
                int col=0;
                int fcol = originalImage.getRGB(i,j);
                if(((fcol>>>d)&1)>0)
                    col=0xffffff;
                transformedImage.setRGB(i, j, col);
            }


//        WritableRaster raster = transformedImage.getRaster();
//        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
//
//        byte[] image = buffer.getData();

//        byte[] image = null;
//
//        try {
//            BufferedImage o = transformedImage;
//            ByteArrayOutputStream b=new ByteArrayOutputStream();
//            ImageIO.write(o, "jpg", b);
//            image=b.toByteArray();
//        } catch (IOException ioException){
//            ioException.printStackTrace();
//        }
//
//
//
//        ColorModel colorModel = transformedImage.getColorModel();
//        pixelSize = colorModel.getPixelSize();
//        int bytesPerPixel = pixelSize / 8;
//        /**
//         * value of bpcsIndex -
//         * [0, 7] blue plane where 0 is MSB and 7 is LSB
//         * [8, 15] green plane where 8 is MSB and 15 is LSB
//         * [16, 23] red plane
//         * if alpha channel is present then [0, 7] alpha plane, 0 is MSB and
//         * all other planes is shifted by one byte
//         * color model is ABGR or BGR (in byte array)
//         */
//        int start = bpcsIndex / 8;
//        bpcsIndex = bpcsIndex % 8;
//        int shift = 7 - bpcsIndex;
//
//        for (int i = 0; i < image.length; i += bytesPerPixel) {
//            for (int j = 0; j < bytesPerPixel; j++) {
//
//                if (j != start) {
//                    image[i + j] = 0x00;
//                }
//
//            }
//
//            int singleBit = (image[i + start] >> shift) & 1;
//
//            if (singleBit == 0) {
//
//                image[i + start] = 0x00;
//            } else {
//
//                image[i + start] = (byte) 128;
//            }
//        }
    }


    //    ----------------------------------------------------------------------------------------



    private void setTransformation() {

        switch (currentTransition) {
            case 0:
                this.transformedImage = originalImage;
                return;
            case 1:
                colourInversion();
                return;
            case 2:
                singlePlane(31);
                return;
            case 3:
                singlePlane(30);
                return;
            case 4:
                singlePlane(29);
                return;
            case 5:
                singlePlane(28);
                return;
            case 6:
                singlePlane(27);
                return;
            case 7:
                singlePlane(26);
                return;
            case 8:
                singlePlane(25);
                return;
            case 9:
                singlePlane(24);
                return;
            case 10:
                singlePlane(23);
                return;
            case 11:
                singlePlane(22);
                return;
            case 12:
                singlePlane(21);
                return;
            case 13:
                singlePlane(20);
                return;
            case 14:
                singlePlane(19);
                return;
            case 15:
                singlePlane(18);
                return;
            case 16:
                singlePlane(17);
                return;
            case 17:
                singlePlane(16);
                return;
            case 18:
                singlePlane(15);
                return;
            case 19:
                singlePlane(14);
                return;
            case 20:
                singlePlane(13);
                return;
            case 21:
                singlePlane(12);
                return;
            case 22:
                singlePlane(11);
                return;
            case 23:
                singlePlane(10);
                return;
            case 24:
                singlePlane(9);
                return;
            case 25:
                singlePlane(8);
                return;
            case 26:
                singlePlane(7);
                return;
            case 27:
                singlePlane(6);
                return;
            case 28:
                singlePlane(5);
                return;
            case 29:
                singlePlane(4);
                return;
            case 30:
                singlePlane(3);
                return;
            case 31:
                singlePlane(2);
                return;
            case 32:
                singlePlane(1);
                return;
            case 33:
                singlePlane(0);
                return;
            default : this.transformedImage = originalImage;
        }
    }

    // Get the current transformation description
    public String getDescription() {

        switch (currentTransition) {

            case 0:
                return "Original Image";

            case 1:
                return "Inverted Colours";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return "Alpha plane " + (9 - currentTransition);
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return "Red plane " + (17 - currentTransition);
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                return "Green plane " + (25 - currentTransition);
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                return "Blue plane " + (33 - currentTransition);

            default:
                return "Original Image";
        }
    }

    //    ----------------------------------------------------------------------------------------

    // Perform the previous transformation
    public void backwardAction() {

        currentTransition--;

        if (currentTransition < 0) {

            currentTransition = MAX_TRANSFORMATIONS;
        }

        setTransformation();
    }

    // Perform the next transformation
    public void forwardAction() {

        currentTransition++;

        if (currentTransition > MAX_TRANSFORMATIONS) {

            currentTransition = 0;
        }

        setTransformation();
    }

    // Initialize the current loaded image
    public void setWorkingImage(BufferedImage image) {

        this.originalImage = image;
        this.transformedImage = originalImage;
        currentTransition = 0;
    }

    // Get the current transformed image
    public BufferedImage getImage() {

        return transformedImage;
    }
}