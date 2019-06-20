//package com.exercise.demo.testCS;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import com.iflytek.cloud.speech.RecognizerListener;
//import com.iflytek.cloud.speech.RecognizerResult;
//import com.iflytek.cloud.speech.Setting;
//import com.iflytek.cloud.speech.SpeechConstant;
//import com.iflytek.cloud.speech.SpeechError;
//import com.iflytek.cloud.speech.SpeechRecognizer;
//import com.iflytek.cloud.speech.SpeechUtility;
//
//public class Test {
//
//        private static final String APPID="5a******1";//这里是自己的APPID
//        private static VoiceTest mObject;
//        private static StringBuilder mResult=new StringBuilder();
//        private String fileName="test.pcm";//这里要将文件拷贝至根目录下，必须是.pcm文件
//
//        //main方法，是否显示日志，语音实用程序验证程序的id
//        public static void main(String[] args) {
//            if(null!=args&&args.length>0&&args[0].equals("true")) {
//                //显示日志
//                Setting.setShowLog(true);
//            }
//
//            SpeechUtility.createUtility("appid="+APPID);
//            getVoiceObj().Recognize();
//        }
//
//        //单例模式，创建对象
//        private static VoiceTest getVoiceObj() {
//            if(mObject==null) {
//                mObject=new VoiceTest();
//            }
//            return mObject;
//        }
//
//
//        //建立语音识别对象
//        private boolean mIsEndOfSpeech=false;
//        private void Recognize() {
//            if(SpeechRecognizer.getRecognizer()==null) {
//                SpeechRecognizer.createRecognizer();
//            }
//            mIsEndOfSpeech=false;
//            RecogizePcmFileBite();
//        }
//
//        //识别音频文件
//        private void RecogizePcmFileBite() {
//            //获取语音识别对象
//            SpeechRecognizer recognizer=SpeechRecognizer.createRecognizer();
//            //设置基本的识别参数,声音来源是音频，结果是自然语言文本
//            recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
//            recognizer.setParameter(SpeechConstant.RESULT_TYPE, "plain");
//            //开始监听,参数是监听器对象
//            recognizer.startListening(recListener);
//            //创建文件输入流
//            FileInputStream fis=null;
//            //创建字节数组，长度为64K
//            byte[] data=new byte[64*1024];
//            try {
//                fis=new FileInputStream(new File("./"+fileName));
//                //文件剩余长度如果没有，就显示没有了
//                if(0==fis.available()) {
//                    mResult.append("no audio avaible!");
//                    //取消语音识别
//                    recognizer.cancel();
//                    //否则有语音文件
//                }else {
//                    int len=data.length;//此时为64*1024即有这么长
//                    while(data.length==len&&!mIsEndOfSpeech) {
//                        //读取文件
//                        len=fis.read(data);
//                        //写出文件
//                        recognizer.writeAudio(data, 0, len);
//                    }
//                    //停止语音识别
//                    recognizer.stopListening();
//                }
//
//            }catch(Exception e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    if(null !=fis) {
//                        fis.close();
//                        fis=null;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //听写监听器
//        private RecognizerListener recListener=new RecognizerListener() {
//            @Override
//            public void onBeginOfSpeech() {
//                DebugLog.Log("onBeginOfSpeech enter");
//                DebugLog.Log("*****开始录音*****");
//
//            }
//
//            @Override
//            public void onVolumeChanged(int volume) {
//                DebugLog.Log( "onVolumeChanged enter" );
//                if (volume > 0)
//                    DebugLog.Log("*************音量值:" + volume + "*************");
//
//            }
//
//            @Override
//            public void onResult(RecognizerResult result, boolean isLast) {
//                DebugLog.Log( "onResult enter" );
//                //获取监听结果的字符串
//                mResult.append(result.getResultString());
//                //如果是结尾
//                if(isLast) {
//                    DebugLog.Log("识别结果为："+mResult.toString());
//                    mIsEndOfSpeech=true;
//                    mResult.delete(0, mResult.length());
//                }
//
//            }
//
//            @Override
//            public void onEvent(int arg0, int arg1, int arg2, String arg3) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onError(SpeechError arg0) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//                DebugLog.Log("onEndOfSpeech enter");
//                DebugLog.Log("*****结束录音*****");
//                mIsEndOfSpeech=true;
//
//            }
//
//        };
//
//}
