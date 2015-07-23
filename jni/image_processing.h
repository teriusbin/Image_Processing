#ifndef _APP_JNIGL_H_
#define _APP_JNIGL_H_

struct SLCEFuncParameter{

	 bool SLCE_MASK;
	 bool SLCE_BT;
	 bool SLCE_MO;
	 bool SLCE_ON;
	 int SLCE_G;
	 int SLCE_CG;
	 bool SLCE_SO;
	 int SLCE_SR;
	 int SLCE_SMD;
	 int SLCE_IG;
	 int SLCE_RO;
	 bool SLCE_RG;
	 int SLCE_REF_GAIN;
	 int SLCE_H;
	 int SLCE_V;
	 int SLCE_BTH;
	 int SLCE_BSR;
	 int SLCE_DTH;
	 int SLCE_MRO;

 };
struct NRFuncParameter{

	 bool NR_E;

 };

struct DE_FAFuncParameter{

	bool DE_E;
	int DE_G;
	int DE_M1;
	int DE_M2;

	bool FE_E;
	int FA_ET;
	int FA_SP;
	int FA_SN;
	int FA_MDG;
	int FA_PP;
	int FA_SC;
	int FA_D;
	long FA_DD;

	int FA_PMW;
	int FA_FMW;
	int FA_SZW;
	int FA_SZH;
	int FA_OC10;
	int FA_OC20C;
 };
struct CSFuncParameter{

	bool CE_E;
	bool CS_E;
 };
struct CCFuncParameter{

	int TestValue;

};
struct ASCRFuncParameter{

	bool ASCR_MASK;
	bool ASCR_BT;
	bool ASCR_MO;
	bool ASCR_AO;
	bool ASCR_LO;
	int ASCR_S;
	int ASCR_SCB;
	int ASCR_SCR;
	int ASCR_DU;
	int ASCR_DD;
	int ASCR_DR;
	int ASCR_DL;
	int ASCR_DDU;
	int ASCR_DDD;
	int ASCR_DDR;
	int ASCR_DDL;
	int ASCR_SRR;
	int ASCR_SRG;
	int ASCR_SRB;
	int ASCR_SYR;
	int ASCR_SYG;
	int ASCR_SYB;
	int ASCR_SMR;
	int ASCR_SMG;
	int ASCR_SMB;
	int ASCR_SWR;
	int ASCR_SWG;
	int ASCR_SWB;
	int ASCR_WCR;
	int ASCR_WRR;
	int ASCR_WCG;
	int ASCR_WRG;
	int ASCR_WCB;
	int ASCR_WRB;
	int ASCR_WMR;
	int ASCR_WGR;
	int ASCR_WMG;
	int ASCR_WGG;
	int ASCR_WMB;
	int ASCR_WGB;
	int ASCR_WYR;
	int ASCR_WBR;
	int ASCR_WYG;
	int ASCR_WBG;
	int ASCR_WYB;
	int ASCR_WBB;
	int ASCR_WWR;
	int ASCR_WKR;
	int ASCR_WWG;
	int ASCR_WKG;
	int ASCR_WWB;
	int ASCR_WKB;

 };

unsigned char **image2DMemAlloc(int height, int width);

unsigned char **memoryCopy1Dto2D(unsigned char *srcdata, int width, int height);

unsigned char *memoryCopy2Dto1D(unsigned char **srcdata, int width, int height);

unsigned char **memoryCopy2DTo2D(unsigned char **inputImage, int width, int height);

unsigned char **SLCEFunc(unsigned char **inputImage, int width, int height , struct SLCEFuncParameter paramSet);

unsigned char **NRFunc(unsigned char **inputImage, int width, int height , struct NRFuncParameter paramSet);

unsigned char **DE_FAFunc(unsigned char **inputImage, int width, int height , struct DE_FAFuncParameter paramSet);

unsigned char **CSFunc(unsigned char **inputImage, int width, int height , struct CSFuncParameter paramSet);

unsigned char **CCFunc(unsigned char **inputImage, int width, int height , struct CCFuncParameter paramSet);

unsigned char **ASCRFunc(unsigned char **inputImage, int width, int height , struct ASCRFuncParameter paramSet);


#endif
