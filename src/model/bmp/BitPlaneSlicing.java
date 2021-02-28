package model.bmp;

import java.awt.*;
import java.awt.image.*;

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
    private static final int MAX_TRANSFORMATIONS = 1;

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

    private void setTransformation() {

        switch (currentTransition) {
            case 0 -> {
                this.transformedImage = originalImage;
            }
            case 1 -> {
                colourInversion();
            }
            default -> this.transformedImage = originalImage;
        }
    }

    // Get the current transformation description
    public String getDescription() {

        switch (currentTransition) {

            case 0:
                return "Original Image";

            case 1:
                return "Inverted Colours";

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
    public void setWorkingImage(BufferedImage image){

        this.originalImage = image;
        this.transformedImage = image;
        currentTransition = 0;
    }

    // Get the current transformed image
    public BufferedImage getImage() {

        return transformedImage;
    }
}