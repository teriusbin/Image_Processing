#ifndef _APP_JNIGL_H_
#define _APP_JNIGL_H_

unsigned char **Image2DMem(int height, int width);

unsigned char **ImageProcessing(unsigned char **inputImage, int width, int height);

unsigned char **MemoryCopy1Dto2D(unsigned char *srcdata, int width, int height);

unsigned char *MemoryCopy2Dto1D(unsigned char **srcdata, int width, int height);

#endif
