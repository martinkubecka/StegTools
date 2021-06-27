package model.image.general;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class BitPlaneSlicing {

    private BufferedImage originalImage;
    private BufferedImage transformedImage;
    private int currentTransition;

    private int maxTransformations;

    private ColorModel colorModel;
    private int bitsPerPixel;
    private boolean isAlphaPresent;
    private int bitIndex;

    /**
     * Initialize the current loaded image.
     * <p>
     *
     * @param image chosen for bit plane slicing.
     * @return boolean value based on if chosen image is supported
     */
    public boolean setWorkingImage(BufferedImage image) {

        this.originalImage = image;
        this.transformedImage = originalImage;

        this.colorModel = image.getColorModel();
        this.bitsPerPixel = colorModel.getPixelSize();
        this.isAlphaPresent = colorModel.hasAlpha();

        if ((bitsPerPixel == 24) || (bitsPerPixel == 32)) {

            maxTransformations = bitsPerPixel + 1;
            currentTransition = 0;
            bitIndex = bitsPerPixel;

            return true;
        }

        return false;
    }

    /**
     * Get the current transformed image.
     * <p>
     *
     * @return transformed image
     */
    public BufferedImage getImage() {

        return transformedImage;
    }

    /**
     * Perform backward action.
     */
    public void backwardAction() {

        currentTransition--;
        bitIndex++;

        if (currentTransition < 0) {

            currentTransition = maxTransformations;
        }

        if (bitIndex > bitsPerPixel) {

            bitIndex = -1;
        }

        setTransformation();
    }


    /**
     * Perform action forward (next transformation).
     */
    public void forwardAction() {

        currentTransition++;
        bitIndex--;

        if (currentTransition > maxTransformations) {

            currentTransition = 0;
        }

        if (bitIndex < -1) {

            bitIndex = bitsPerPixel;
        }

        setTransformation();
    }

    /**
     * Perform desired transformation based on the bit index.
     */
    private void setTransformation() {

        if (bitIndex == bitsPerPixel) {

            this.transformedImage = originalImage;

        } else if (bitIndex == -1) {

            colourInversion();

        } else {

            singlePlane(bitIndex);
        }
    }

    /**
     * Get the current transformation description.
     * <p>
     *
     * @return current transformation description
     */
    public String getDescription() {

        if (isAlphaPresent) {

            if ((31 >= bitIndex) && (bitIndex >= 24)) {

                return ("Alpha plane no." + (bitIndex - 24));
            }
        }

        if ((23 >= bitIndex) && (bitIndex >= 16)) {

            return ("Red plane no." + (bitIndex - 16));

        } else if ((15 >= bitIndex) && (bitIndex >= 8)) {

            return ("Green plane no." + (bitIndex - 8));

        } else if ((7 >= bitIndex) && (bitIndex >= 0)) {

            return ("Blue plane no." + (bitIndex));

        } else if (bitIndex == -1) {

            return ("Inverted Colours");
        }

        return "Original Image";
    }

    /* --------------------------------------------------------------------------------------------- */

    /**
     * Perform image colour inversion.
     * <p>
     * Consider a color pixel with the following values:
     * <p>
     * A = 255 ; R = 100 ; G = 150 ; B = 200
     * <p>
     * ARGB have an integer value in the [0 ; 255]
     * <p>
     * Convert the color pixel into negative:
     * - Subtract the value of R, G and B from 255
     * <p>
     * R = 255 - 100 = 155
     * G = 255 - 150 = 105
     * B = 255 - 200 = 55
     * <p>
     * The new ARGB value will be:
     * <p>
     * A = 255 ; R = 155 ; G = 105 ; B = 55
     * <p>
     * We don't have to change the alpha value
     * because it only controls the transparency of the pixel
     */
    private void colourInversion() {

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        transformedImage = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_ARGB);

        int pixelColor = 0;

        for (int i = 0; i < originalImage.getWidth(); i++) {
            for (int j = 0; j < originalImage.getHeight(); j++) {

                pixelColor = originalImage.getRGB(i, j);

                Color color = new Color(pixelColor);
                color = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());

                transformedImage.setRGB(i, j, color.getRGB());
            }
        }
    }

    /**
     * Create an image bitmap representing the current bit plane.
     * <p>
     * Shift the value by d bits, it the shifted value has its last bit set to 1 set the current pixel color to white, else it is set to black.
     * <p>
     *
     * @param d index of the current bit plane
     */
    private void singlePlane(int d) {

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        transformedImage = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < originalImage.getWidth(); i++)
            for (int j = 0; j < originalImage.getHeight(); j++) {

                int setColor = 0; // BLACK
                int pixelColor = originalImage.getRGB(i, j);

                if (((pixelColor >>> d) & 1) > 0) {

                    setColor = 0xffffff; // WHITE
                }

                transformedImage.setRGB(i, j, setColor);
            }
    }
}