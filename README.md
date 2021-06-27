<p align="center">
<img src="https://github.com/martinkubecka/StegTools/blob/master/src/resources/images/logo.png" alt="Stegtools">
<p align="center">Steganographic multi tool developed as a part of my bachelor's thesis.<br>
</p>

---
## :mag_right: Functionalities

### LSB Insertion and Extraction
- Supported image format : BMP

### Bit Plane Slicing
- RGB images with 8‑bits per channel (24‑bit or 32-bit images) are supported.

<p align="center">
<img src="https://github.com/martinkubecka/StegTools/blob/master/src/resources/images/bit-plane-viewer.png" alt="Bit Plane Viewer">
<p align="center">The second most significant bit plane of the Blue channel<br>
</p>

### Automatic Repair of Corrupted Image Header
- Supported image format : PNG

### Extraction of Appended Data
- Supported image formats : BMP, PNG

<p align="center">
<img src="https://github.com/martinkubecka/StegTools/blob/master/src/resources/images/extracted-data.png" alt="Appended Data">
<p align="center">Extraction and conversation of appended data<br>
</p>

### Image Metadata Extraction
- Supported image formats : BMP, PNG, JPEG, GIF, TIFF and RAW

<p align="center">
<img src="https://github.com/martinkubecka/StegTools/blob/master/src/resources/images/metadata.png" alt="Metadata Extraction">
<p align="center">Extracted image metadata<br>
</p>

### Message Shortening with Synonym Dictionary
- Supported file format : TXT

### Password Protected Compression and Decompression

---
## :hammer: Development
| Functionality  | Implemented | Updated |
| ------------- | ------------- | ------------- |
| Show JPEG image Metadata  | v0.2  | v0.7  | 
| Password protected compression of chosen files  | v0.2  | v0.7  |
| Extraction of files from a password-protected zip | v0.4 | v0.81 |
| Message shortening with synonym dictionary | v0.4 | v0.82 |
| Check for PNG header correctness | v0.85 | |
| Automatic repair of corrupted png header | v0.85 | |
| Show BMP, GIF, TIFF and RAW image Metadata | v0.86 | |
| Bit Plane Slicing - Inverted Colours | v0.91 | |
| Bit Plane Slicing for 24‑bit and 32-bit images | v0.91 | |
| Appended data extraction for PNG and BMP image files | v0.93 | |
| LSB Insertion for BMP images | v0.95
| LSB Extraction for BMP images | v0.98

---
## :gear: Pre-requisites

Stegtools software requires [Java SE Development Kit](https://www.oracle.com/java/technologies/javase-downloads.html) 15, or a newer edition, for successful operation.

---
## :running: Running Stegtools

1. Donwload the [dist](https://github.com/martinkubecka/StegTools/tree/master/dist) folder.
2. Launch the application by running **Stegtools.jar** located in this folder.<br>

If we want to run the application from the terminal, we will use the following command ```java -jar StegTools.jar```.

---
## :books: Documentation

Download [doc](https://github.com/martinkubecka/StegTools/tree/master/dist/doc) folder and run ```index.html```.
