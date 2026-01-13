/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Dog;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * The {@code PhotoHelper} class provides utility methods for handling dog
 * photos throughout the application. It offers image scaling, default photo
 * handling, and consistent photo sizing for different UI components.
 *
 * <p>
 * This class is designed to be used across all parts of the application (Admin
 * Panel, User Panel, Reports, etc.) to ensure consistent photo display and
 * reduce code duplication.</p>
 *
 * <p>
 * All methods are static, allowing easy access without object instantiation.
 * The class handles null and invalid photo paths gracefully by returning
 * default placeholder images.</p>
 *
 * @author Shahil
 * @see ImageIcon
 * @see Image
 */
public class PhotoHelper {

    /**
     * Default path to the fallback dog photo used when a dog has no photo or
     * when the photo path is invalid.
     */
    public static final String DEFAULT_PHOTO_PATH
            = "src\\assets\\dogImages\\default_dog.jpg";

    /**
     * Size (in pixels) for thumbnail images.
     */
    public static final int THUMBNAIL_SIZE = 60;

    /**
     * Size (in pixels) for preview images.
     */
    public static final int PREVIEW_SIZE = 150;

    /**
     * Size (in pixels) for profile images.
     */
    public static final int PROFILE_SIZE = 250;

    /**
     * Maximum size (in pixels) for full-view images. Images larger than this
     * will be scaled down to fit within this maximum dimension.
     */
    public static final int FULL_VIEW_MAX = 600;

    /**
     * Scales a photo from the given path to the specified dimensions. If the
     * photo cannot be loaded, returns a default scaled icon.
     *
     * <p>
     * <b>Usage Example:</b></p>
     * <pre>
     * {@code
     * ImageIcon icon = PhotoHelper.getScaledIcon("path/to/photo.jpg", 150, 150);
     * photoLabel.setIcon(icon);
     * }
     * </pre>
     *
     * @param photoPath the file path to the photo. Can be {@code null} or empty.
     * @param width the desired width of the scaled image in pixels
     * @param height the desired height of the scaled image in pixels
     * @return a scaled {@code ImageIcon}, or a default icon if scaling fails
     */
    public static ImageIcon getScaledIcon(String photoPath, int width, int height) {
        if (photoPath == null || photoPath.trim().isEmpty()) {
            return getDefaultIcon(width, height);
        }

        try {
            ImageIcon original = new ImageIcon(photoPath);
            return scaleImageIcon(original, width, height);
        } catch (Exception e) {
            return getDefaultIcon(width, height);
        }
    }

    /**
     * Gets a scaled photo from a Dog object. Convenience method that extracts
     * the photo path from the Dog object and scales it to a square of the given
     * size.
     *
     * @param dog the Dog object containing the photo path. Can be {@code null}.
     * @param size the desired width and height of the square image in pixels
     * @return a scaled square {@code ImageIcon} for the dog's photo
     */
    public static ImageIcon getDogPhoto(Dog dog, int size) {
        return getScaledIcon(dog != null ? dog.getPhotoPath() : null, size, size);
    }

    /**
     * Gets a thumbnail-sized icon suitable for tables or lists. Uses the
     * predefined {@link #THUMBNAIL_SIZE}.
     *
     * @param photoPath the file path to the photo
     * @return a thumbnail {@code ImageIcon} (60x60 pixels)
     */
    public static ImageIcon getThumbnail(String photoPath) {
        return getScaledIcon(photoPath, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
    }

    /**
     * Gets a preview-sized icon suitable for form previews. Uses the predefined
     * {@link #PREVIEW_SIZE}.
     *
     * @param photoPath the file path to the photo
     * @return a preview {@code ImageIcon} (150x150 pixels)
     */
    public static ImageIcon getPreview(String photoPath) {
        return getScaledIcon(photoPath, PREVIEW_SIZE, PREVIEW_SIZE);
    }

    /**
     * Gets a profile-sized icon suitable for user profiles. Uses the predefined
     * {@link #PROFILE_SIZE}.
     *
     * @param photoPath the file path to the photo
     * @return a profile {@code ImageIcon} (250x250 pixels)
     */
    public static ImageIcon getProfile(String photoPath) {
        return getScaledIcon(photoPath, PROFILE_SIZE, PROFILE_SIZE);
    }

    /**
     * Gets the photo in full view size. If the original photo is smaller than
     * the maximum dimensions, returns the original. Otherwise, scales it down
     * to fit within {@link #FULL_VIEW_MAX} pixels while maintaining aspect
     * ratio.
     *
     * @param photoPath the file path to the photo
     * @return the original or scaled {@code ImageIcon} for full-view display
     */
    public static ImageIcon getFullView(String photoPath) {
        if (photoPath == null) {
            return getDefaultIcon(FULL_VIEW_MAX, FULL_VIEW_MAX);
        }

        try {
            ImageIcon original = new ImageIcon(photoPath);

            // If image is already smaller than max size, return original
            if (original.getIconWidth() <= FULL_VIEW_MAX
                    && original.getIconHeight() <= FULL_VIEW_MAX) {
                return original;
            }

            // Scale down to fit within max dimensions while keeping aspect ratio
            return scaleToFit(original, FULL_VIEW_MAX, FULL_VIEW_MAX);
        } catch (Exception e) {
            return getDefaultIcon(FULL_VIEW_MAX, FULL_VIEW_MAX);
        }
    }

    /**
     * Gets a default/placeholder icon scaled to the specified dimensions. Used
     * when a dog has no photo or when photo loading fails.
     *
     * @param width the desired width in pixels
     * @param height the desired height in pixels
     * @return a default {@code ImageIcon} with the specified dimensions
     */
    public static ImageIcon getDefaultIcon(int width, int height) {
        try {
            return getScaledIcon(DEFAULT_PHOTO_PATH, width, height);
        } catch (Exception e) {
            // If default photo fails, create a colored placeholder
            return null;
        }
    }

    /**
     * Scales an ImageIcon to the specified dimensions using smooth scaling.
     *
     * @param original the original ImageIcon to scale
     * @param width the target width in pixels
     * @param height the target height in pixels
     * @return a new scaled ImageIcon
     * @throws NullPointerException if original is null
     */
    private static ImageIcon scaleImageIcon(ImageIcon original, int width, int height) {
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Scales an image to fit within maximum dimensions while preserving aspect
     * ratio. The image will be scaled down only if it exceeds the maximum
     * dimensions.
     *
     * @param original the original ImageIcon
     * @param maxWidth the maximum allowed width in pixels
     * @param maxHeight the maximum allowed height in pixels
     * @return a new ImageIcon scaled to fit within the maximum dimensions
     */
    private static ImageIcon scaleToFit(ImageIcon original, int maxWidth, int maxHeight) {
        // Calculate scaling ratio to fit within max dimensions
        double widthRatio = (double) maxWidth / original.getIconWidth();
        double heightRatio = (double) maxHeight / original.getIconHeight();
        double ratio = Math.min(widthRatio, heightRatio);

        // Calculate new dimensions
        int newWidth = (int) (original.getIconWidth() * ratio);
        int newHeight = (int) (original.getIconHeight() * ratio);

        return scaleImageIcon(original, newWidth, newHeight);
    }

    /**
     * Creates a simple colored placeholder image with "No Photo" text. Used as
     * a fallback when all other photo loading methods fail.
     *
     * @param width the width of the placeholder in pixels
     * @param height the height of the placeholder in pixels
     * @return a generated placeholder ImageIcon
     */
    private static ImageIcon createPlaceholder(int width, int height) {
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = img.createGraphics();

        // Fill with light gray background
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        // Add border
        g.setColor(java.awt.Color.DARK_GRAY);
        g.drawRect(0, 0, width - 1, height - 1);

        // Add "No Photo" text
        g.setColor(java.awt.Color.DARK_GRAY);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN,
                Math.min(14, width / 10)));

        String text = "No Photo";
        java.awt.FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        g.drawString(text,
                (width - textWidth) / 2,
                (height + textHeight) / 2 - 5);

        g.dispose();
        return new ImageIcon(img);
    }

    /**
     * Utility method to check if a photo file exists and is readable.
     *
     * @param photoPath the path to check
     * @return {@code true} if the file exists and is readable, {@code false}
     * otherwise
     */
    public static boolean isValidPhotoPath(String photoPath) {
        if (photoPath == null || photoPath.trim().isEmpty()) {
            return false;
        }

        try {
            java.io.File file = new java.io.File(photoPath);
            return file.exists() && file.canRead() && file.length() > 0;
        } catch (SecurityException e) {
            return false;
        }
    }

    /**
     * Gets the dimensions of a photo without loading the full image into
     * memory.
     *
     * @param photoPath the path to the photo file
     * @return an array of two integers [width, height], or [0, 0] if unable to
     * read
     */
    public static int[] getPhotoDimensions(String photoPath) {
        if (!isValidPhotoPath(photoPath)) {
            return new int[]{0, 0};
        }

        try {
            ImageIcon icon = new ImageIcon(photoPath);
            return new int[]{icon.getIconWidth(), icon.getIconHeight()};
        } catch (Exception e) {
            return new int[]{0, 0};
        }
    }
}
