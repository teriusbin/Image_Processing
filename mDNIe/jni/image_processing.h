#ifndef _APP_JNIGL_H_
#define _APP_JNIGL_H_

struct ImageProcessing1{

	int algo1_param1;
	float algo1_param2;
	double algo1_param3;
	long algo1_param4;

 };
struct ImageProcessing2{

	int algo2_param1;
	float algo2_param2;
	double algo2_param3;


 };
struct ImageProcessing3{

	int algo3_param1;
	float algo3_param2;
	double algo3_param3;

 };

unsigned char **image2DMemAlloc(int height, int width);

unsigned char **memoryCopy1Dto2D(unsigned char *srcdata, int width, int height);

unsigned char *memoryCopy2Dto1D(unsigned char **srcdata, int width, int height);

unsigned char **memoryCopy2DTo2D(unsigned char **inputImage, int width, int height);




unsigned char **ImageProcessing1(unsigned char **inputImage, int width, int height , struct ImageProcessing1 paramSet);

unsigned char **ImageProcessing2(unsigned char **inputImage, int width, int height , struct ImageProcessing2 paramSet);

unsigned char **ImageProcessing3(unsigned char **inputImage, int width, int height , struct ImageProcessing3 paramSet);




#endif
