#ifndef _APP_JNIGL_H_
#define _APP_JNIGL_H_

struct ImageProcessing1{

	int param1;

 };
struct ImageProcessing2{

	int param1;

 };
struct ImageProcessing3{

	int param1;

 };

unsigned char **Image2DMem(int height, int width);

unsigned char **ImageProcessing1(unsigned char **inputImage, int width, int height , struct ImageProcessing1 paramSet);

unsigned char **ImageProcessing2(unsigned char **inputImage, int width, int height , struct ImageProcessing2 paramSet);

unsigned char **ImageProcessing3(unsigned char **inputImage, int width, int height , struct ImageProcessing3 paramSet);

unsigned char **MemoryCopy1Dto2D(unsigned char *srcdata, int width, int height);

unsigned char *MemoryCopy2Dto1D(unsigned char **srcdata, int width, int height);

unsigned char **memcpy2DTo2D(unsigned char **inputImage, int width, int height);


#endif
