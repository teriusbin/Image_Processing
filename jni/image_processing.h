#ifndef _APP_JNIGL_H_
#define _APP_JNIGL_H_



struct FSFuncParameter{

	int FS_param1;
	float FS_param2;
	double FS_param3;
	long FS_param4;

 };
struct HDRGlareFuncParameter{

	int HDRGlare_param1;
	float HDRGlare_param2;
	double HDRGlare_param3;

 };
struct NRFuncParameter{

	int NR_param1;
	float NR_param2;
	double NR_param3;

 };
struct FADEFuncParameter{

	int FADE_param1;
	float FADE_param2;
	double FADE_param3;

 };
struct CSFuncParameter{

	int CS_param1;
	float CS_param2;
	double CS_param3;

 };
struct CCFuncParameter{

	int CC_param1;
	float CC_param2;
	double CC_param3;

 };
struct ASCRFuncParameter{

	int ASCR_param1;
	float ASCR_param2;
	double ASCR_param3;

 };

unsigned char **image2DMemAlloc(int height, int width);

unsigned char **memoryCopy1Dto2D(unsigned char *srcdata, int width, int height);

unsigned char *memoryCopy2Dto1D(unsigned char **srcdata, int width, int height);

unsigned char **memoryCopy2DTo2D(unsigned char **inputImage, int width, int height);



unsigned char **FSFunc(unsigned char **inputImage, int width, int height , struct FSFuncParameter paramSet);

unsigned char **HDRGlareFunc(unsigned char **inputImage, int width, int height , struct HDRGlareFuncParameter paramSet);

unsigned char **NRFunc(unsigned char **inputImage, int width, int height , struct NRFuncParameter paramSet);

unsigned char **FADEFunc(unsigned char **inputImage, int width, int height , struct FADEFuncParameter paramSet);

unsigned char **CSFunc(unsigned char **inputImage, int width, int height , struct CSFuncParameter paramSet);

unsigned char **CCFunc(unsigned char **inputImage, int width, int height , struct CCFuncParameter paramSet);

unsigned char **ASCRFunc(unsigned char **inputImage, int width, int height , struct ASCRFuncParameter paramSet);


#endif
