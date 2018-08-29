package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstAidActivity extends AppCompatActivity {
    TextView TextView_name;
    TextView TextView_contents;
    Button first_aid_re_list;
    Button first_aid_gotohome;

    HashMap<String,String> firstAidMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        TextView_name = (TextView) findViewById(R.id.NameText);
        TextView_contents = (TextView) findViewById(R.id.ContentsText);

        first_aid_re_list = findViewById(R.id.first_aid_re_list);
        first_aid_gotohome = findViewById(R.id.first_aid_gotohome);

        first_aid_re_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        first_aid_gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstAidActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });
        String name;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
            } else {
                name= extras.getString("NAME");
            }
        }else {
            name= (String) savedInstanceState.getSerializable("NAME");
        }
        TextView_name.setText(name);
        setFirstAidMap();
        TextView_contents.setMovementMethod(new ScrollingMovementMethod());


        TextView_contents.setText(firstAidMap.get(name));
    }


    private void setFirstAidMap(){
//        firstAidMap.put("골절","척추 골절)\n 환자를 움직이지 말고 손으로 머리를 고정하라! \n 감은 옷을 따로 대어 환자를 지지하라!\n\n"+
//                "팔의 골절)\n 상처 입은 팔을 가슴에 대고 가슴과 팔 지지하라!\n 가슴과 팔 사이에 부드러운 헝겊 조각 같은 것을 끼워 주라!\n\n "+
//                "골반 골절)\n 다리를 펴준 채로 환자를 눕히거나 무릎을 구부리는 것이 더 편안하다고 하면 무릎 밑에 담요를 말아서 대고 다리를 묶어서 고정하는데 관절 사이에는 패드를 넣어라! \n\n "+
//                "발의 골절)\n 아픈 부위의 발을 들고 발바닥에 헝겊을 대고 부목을 받쳐준 후 고정하라!\n\n "+
//                "쇄골 골절)\n 환자를 앉히고, 손상된 쪽 팔을 가슴을 지나 반대쪽으로 가게 하라! \n 넓은 천으로 다친 쪽 팔을 가슴에 고정시켜라! (삼각건으로 고정)"
//
//        );
//        firstAidMap.put("기도폐쇄","1. 다 막히지 않은 경우 (일부만 막힌 경우)\n\n"+
//                "숨이 통하는 경우에는 계속 기침을 하도록 유도하며, 계속해서 기침을 해도 이물질이 배출되지 않을 때에는 즉시 119로 연락을 하여 도움을 요청합니다.\n\n\n" +
//                "2.꽉 막힌 경우\n\n"+"① 정신을 잃지 않고, 숨길이 막힌 환자의 처치법(복부 밀쳐 올리기)\n\n" +
//                "환자가 정신을 잃지 않았다면 당신은 환자의 뒤로 돌아가서, 환자의 허리를 껴안습니다.\n" +
//                " 당신의 한 손은 주먹을 쥐어 환자의 상복부, 즉 배꼽과 명치끝의 중간 부분에 대고 다른 손으로는 주먹 쥔 손을 감싼 후에 환자의 복부를 당신의 가슴 쪽으로 강하게 끌어올리는 방법입니다.\n 5회 연속 압박을 실행한 후에는 환자의 입안을 살피고, 입안에서 이물질이 발견되면 제거하고, 이물질이 관찰되지 않으면 다시 복부 밀쳐 올리기를 5회 시행합니다.\n\n\n" +
//                "② 의식이 없는 환자의 완전 기도폐쇄 처치법\n\n" +
//                "쓰러진 환자를 바로 눕힙니다. 당신은 환자의 옆에 무릎을 대고 앉아서, 한 손으로는 주먹을 쥐어 환자의 상복부, 즉 배꼽과 명치끝의 중간 부분에 대고 다른 손으로는 주먹 쥔 손을 감싼 후에 환자의 복부를 환자의 가슴 쪽과 등 쪽으로 강하게 쳐 올리는 방법입니다.\n 5회 연속 압박을 실행한 후에는 환자의 입안을 살피고, 입안에서 이물질이 발견되면 제거하고, 이물질이 관찰되지 않으면 다시 복부 밀쳐 올리기를 5회 시행합니다."
//        );

//        firstAidMap.put("당뇨 응급상황","과일 주스나 캔디, 당분이든 음료수를 준다.(다이어트 음료수는 당분을 함유하지 않은 경우가 많으므로 사용하지 않는다.)\n"+
//                "주스 등의 당분을 제공한 후 10분이 경과 후에도 증상이 남아 있는 경우 다시 제공한다.\n"+
//                "15분이 지나도 증상의 회복이 없으면 응급실로 후송하는 것이 좋다.\n\n"+
//                "고혈당 환자)\n"+
//                "환자가 인슐린이나 당뇨약(경구 혈당 강하제) 먹는 것을 빼 먹었다면 이를 먹을 수 있게 도와준다.\n"+
//                "환자에게 당이 없는 물을 먹인다.\n"+
//                "15분 이상 증세의 호전이 없는 경우 병원으로 이송한다.\n\n"+
//                "저혈당, 고혈당 판단이 어려운 경우)\n"+
//                "소량의 각설탕을 혀 밑에 넣어본다.\n"+
//                "환자의 기도 유지, 호흡유무, 맥박 유무를 확인하고 119 구급대에 신고한다.\n"+
//                "환자의 쇼크 유발 방지를 위해 다리를 높이고 담요로 따뜻하게 해준다.\n"+
//                "환자가 혹시 척추 손상이 있을 수 있으므로 환자를 옮기지 말아야 한다.\n"+
//                "당이 들어있는 음식이나 물을 먹인다.\n" +
//                "처치 후 15분 이상 증세의 호전이 없는 경우 병원으로 이송한다."
//        );

        firstAidMap.put("척추 골절","\n 환자를 움직이지 말고 손으로 머리를 고정하라! \n 감은 옷을 따로 대어 환자를 지지하라!\n\n");
        firstAidMap.put("팔 골절","\n 상처 입은 팔을 가슴에 대고 가슴과 팔 지지하라!\n 가슴과 팔 사이에 부드러운 헝겊 조각 같은 것을 끼워 주라!\n\n");
        firstAidMap.put("골반 골절","\n 다리를 펴준 채로 환자를 눕히거나 무릎을 구부리는 것이 더 편안하다고 하면 무릎 밑에 담요를 말아서 대고 다리를 묶어서 고정하는데 관절 사이에는 패드를 넣어라! \n\n ");
        firstAidMap.put("발 골절","\n 아픈 부위의 발을 들고 발바닥에 헝겊을 대고 부목을 받쳐준 후 고정하라!\n\n ");
        firstAidMap.put("쇄골 골절","\n 환자를 앉히고, 손상된 쪽 팔을 가슴을 지나 반대쪽으로 가게 하라! \n 넓은 천으로 다친 쪽 팔을 가슴에 고정시켜라! (삼각건으로 고정)");

        firstAidMap.put("다 막히지 않은 경우","\n 숨이 통하는 경우에는 계속 기침을 하도록 유도하며, 계속해서 기침을 해도 이물질이 배출되지 않을 때에는 즉시 119로 연락을 하여 도움을 요청합니다.\n\n");
        firstAidMap.put("정신을 잃었을 경우","\n정신을 잃지 않고, 숨길이 막힌 환자의 처치법(복부 밀쳐 올리기)\n\n" +
                "환자가 정신을 잃지 않았다면 당신은 환자의 뒤로 돌아가서, 환자의 허리를 껴안습니다.\n" +
                " 당신의 한 손은 주먹을 쥐어 환자의 상복부, 즉 배꼽과 명치끝의 중간 부분에 대고 다른 손으로는 주먹 쥔 손을 감싼 후에 환자의 복부를 당신의 가슴 쪽으로 강하게 끌어올리는 방법입니다.\n 5회 연속 압박을 실행한 후에는 환자의 입안을 살피고, 입안에서 이물질이 발견되면 제거하고, 이물질이 관찰되지 않으면 다시 복부 밀쳐 올리기를 5회 시행합니다.\n\n\n");
        firstAidMap.put("정신을 잃지 않았을 경우", "의식이 없는 환자의 완전 기도폐쇄 처치법\n\n" +
                "쓰러진 환자를 바로 눕힙니다. 당신은 환자의 옆에 무릎을 대고 앉아서, 한 손으로는 주먹을 쥐어 환자의 상복부, 즉 배꼽과 명치끝의 중간 부분에 대고 다른 손으로는 주먹 쥔 손을 감싼 후에 환자의 복부를 환자의 가슴 쪽과 등 쪽으로 강하게 쳐 올리는 방법입니다.\n 5회 연속 압박을 실행한 후에는 환자의 입안을 살피고, 입안에서 이물질이 발견되면 제거하고, 이물질이 관찰되지 않으면 다시 복부 밀쳐 올리기를 5회 시행합니다.");




        firstAidMap.put("뇌수막염","1. 환자가 뻣뻣한 목에 대해 불편함을 호소할 수 있다. 또한 플루 같은 증상, 근육과 관절 통증, 두통, 고온, 빛에 민감한 반응을 보일 수 있다.\n"+
        "2. 이런 증상이 관찰된다면, 119에 신고한다.\n"+
                "3. 119구급대를 기다리는 동안 계속해서 안심시켜준다."
        );



        firstAidMap.put("뇌졸중","시간을 지체하지 말고 구급차를 부르거나 병원으로 이송한다. 혈전에 의한 뇌혈관 폐쇄가 원인일 경우 혈전 용해제 투여가 중요하며, 발생 후 3시간 이내에 처치가 이루어져야 한다.\n" +
                "인후근육 마비나 구토 등으로 기도가 막힐 수 있으므로 기도 유지에 유의한다.\n" +
                "환자를 눕혀 허리와 어깨를 약간 올려주면 뇌의 혈압이 낮아진다.\n" +
                "의식이 저하되거나 없는 환자에게 마실 것이나 먹을 것을 주지 않는다. 음식물을 삼키는 기능이 마비되어 기도폐쇄를 유발시킬 수 있다.");


        firstAidMap.put("저혈당 환자","과일 주스나 캔디, 당분이든 음료수를 준다.(다이어트 음료수는 당분을 함유하지 않은 경우가 많으므로 사용하지 않는다.)\n"+
                        "주스 등의 당분을 제공한 후 10분이 경과 후에도 증상이 남아 있는 경우 다시 제공한다.\n"+
                        "15분이 지나도 증상의 회복이 없으면 응급실로 후송하는 것이 좋다.\n\n");
        firstAidMap.put("고혈당 환자","환자가 인슐린이나 당뇨약(경구 혈당 강하제) 먹는 것을 빼 먹었다면 이를 먹을 수 있게 도와준다.\n"+
                "환자에게 당이 없는 물을 먹인다.\n"+
                "15분 이상 증세의 호전이 없는 경우 병원으로 이송한다.\n\n");

        firstAidMap.put("판단이 어려운 경우","소량의 각설탕을 혀 밑에 넣어본다.\n"+
                "환자의 기도 유지, 호흡유무, 맥박 유무를 확인하고 119 구급대에 신고한다.\n"+
                "환자의 쇼크 유발 방지를 위해 다리를 높이고 담요로 따뜻하게 해준다.\n"+
                "환자가 혹시 척추 손상이 있을 수 있으므로 환자를 옮기지 말아야 한다.\n"+
                "당이 들어있는 음식이나 물을 먹인다.\n" +
                "처치 후 15분 이상 증세의 호전이 없는 경우 병원으로 이송한다.");




        firstAidMap.put("머리손상","출혈부위를 지혈합니다.기도를 확보하여 호흡을 유지시킨 후 맥박을 확인합니다.\n" +
                "경추손상이 우려되는 경우 경추고정을 실시하고 이송합니다.\n" +
                "귀나 코로부터 맑은 물(뇌척수액)이 흐르는 경우 소독된 거즈로 살며시 덮어 두되 절대 탄력붕대 등으로 압박하지 말아야 합니다.\n" +
                "두부손상만 있는 경우에는 머리부위를 30도 정도 올려주어 뇌부종을 줄여줍니다.\n" +
                "환자의 몸을 보온하지 않습니다.(외부온도가 21℃ 이상이면 담요를 덮지 않습니다.)");


        firstAidMap.put("무의식","가. 환자가 숨을 쉴 수 있도록 숨길을 열어줍니다.(기도유지)\n" +
                "의식을 잃은 환자는 혀가 뒤로 말려 들어가 기도가 막힐 수 있으므로 환자의 머리를 뒤로 젖히고, 턱을 들어주어 기도를 유지합니다. 그러나 사고에 의한 경우에는 경추손상(목뼈가 부러짐)이 있을 가능성이 있으므로 턱만 살며시 들어줍니다.\n\n"+
                "나. 숨을 쉬는지 10초가 확인합니다.\n" +
                "기도를 유지한 상태에서 눈으로 가슴의 움직임을 관찰하고, 귀로는 호흡음을 들으며, 뺨의 촉감을 이용하여 호흡유무를 10초 이내에 확인합니다.\n\n"+
                "다. 환자가 숨을 안 쉬면, 당신은 당신의 숨을 두 번 불어넣습니다.\n"+
                "ⓐ 이마를 누르면서 턱을 들어 기도를 유지한 다음 환자의 입을 벌린다.\n" +
                "ⓑ 환자의 코를 막고 자신의 입을 환자의 입에 밀착시킨다.\n" +
                "ⓒ 공기를 서서히(2초)불어 넣는다. 불어넣는 양은 보통 숨쉬는 양(500cc)보다 조금 많게(600-900cc)한다.\n" +
                "ⓓ 잡았던 코를 놓고 입을 떼어 불어넣은 공기가 밖으로 배출될 수 있도록 한다.\n" +
                "ⓔ 입으로 인공호흡을 할 수 없을 때는 입을 막고 코로 인공호흡을 할 수 있다.\n\n"+
                "라. 환자의 심장이 뛰는지를 확인합니다.\n"+
                "심장정지를 확인하기 위해서는 숨을 쉬거나 기침을 하는지? 또는 조금이라도 움직이는지를 확인하는데, 10초 이내에 확인해야 합니다. 즉, 호흡확인과 같은 요령으로 하면서 눈으로 움직임을 살피는 것입니다.\n\n"+
                "마. 가슴(흉부)압박을 합니다.\n"+
                "심장이 확실히 뛰지 않는다면 흉부를 압박해야 하는데, 압박하는 위치와 압박하는 깊이를 정확히 알고 있어야 합니다. 압박할 위치 위에 한 손을 올려놓고 그 위에 다른 손을 올려 깍지를 낍니다. 이때 환자는 바닥이 평평하고 단단한 곳에 수평자세에서 흉부압박을 하여야 합니다.\n\n"+
                "☞ 흉부압박점 찾기\n"+
                "흉골의 가운데를 압박하면 되지만, 좌․우의 갈비뼈가 만나는 곳인 명치 끝(검상돌기)에서 두 손가락 넓이만큼 위쪽이 정확한 위치이다.\n\n"+
                "☞ 흉부압박하기\n"+
                "ⓐ 흉부압박점에 한 손을 올려놓고 그 위에 다른 손을 겹쳐서 깍지를 껴서 손가락이 흉벽(갈비뼈)에 닿지 않도록 하라!\n" +
                "ⓑ 팔꿈치를 곧게 펴고 어깨와 손목이 팔과 일직선이 되게 하라! \n" +
                "ⓒ 흉골 위에 수직으로 당신의 체중이 실리도록 한 다음 압박해라!\n" +
                "ⓓ 성인의 경우에는 흉부가 압박되는 깊이는 가슴이 4～5cm정도 함몰되도록 압박해라!\n\n"+
                "바. 호흡과 순환확인(재평가)을 합니다.\n"+
                "1분 동안 심폐소생술을 시행 한 후에 다시 심장과 호흡이 되돌아왔는지 확인을 합니다. 회복되지 않았을 경우 119구급대원이 도착할 때까지 계속시행 합니다."
    );


        firstAidMap.put("발작/간질","1. 환자가 부상을 입지 않도록 주변에 위험한 물건들을 치우십시오.\n" +
                "2. 환자를 평평한 바닥에 편히 눕게하고 머리에는 베개나 방석을 베어줍니다.\n 더불어 옷의 단추나 혁대등을 풀어 환자가 옷으로 받는 압박을 줄여줍니다.\n"+
                "3. 입 안에 있는 침과 토사물이 나올 수 있도록 환자의 고개를 옆으로 돌려주는 것이 필요합니다.\n"+
                "4. 발작이 끝나면 환자가 잠을 자거나 휴식을 취할 수 있도록 도와줍니다.\n 곁에 있다가 깨어날 때에는 친절하고 부드러운 태도로 혼란스런 환자를 안심시켜줍니다.\n\n"+
                "※해서는 안될 행동\n"+
                "1. 억지로 환자를 움직이지 못하게 묶거나 붙잡지 않습니다.\n"+
                "2. 손을 따거나 사지를 주물러 발작을 완하하려는 행동은 실제로 아무런 도움을 주지 못합니다.\n"+
                "3. 입을 벌리겠다고 막대기나 손가락 등을 넣어서는 안됩니다. 환자의 입 안에 상처가 날 수 있으며 손가락을 깨물면 2차 사고가 발생할 수 있습니다.\n"+
                "4. 물이나 음식을 주면 기도가 막혀 호흡 곤란에 시달릴 수 있습니다. 발작이 완전히 사라진 뒤에 제공해야 합니다."
        );

        firstAidMap.put("심리적 응급처치",
                "1. 곁에 머물러라\n"+
                        "누군가가 곁에 머물러 주면 빠르게 신뢰 및 안정감을 회복하고 타인의 불안이나 극단적 감정 표출에 놀라지 않을 수 있습니다.\n\n"+
                        "2. 경청하라.\n"+
                        "이야기를 들어주고 피해자 옆에 있어주는 것은 중요합니다. 이야기를 하다보면 사건을 더 이해하게 되고 결국 더 쉽게 받아들이게 됩니다.\n\n"+
                        "3. 감정을 받아들여라.\n"+
                        "열린 마음으로 가지고 피해자들의 말을 듣고 사건에 대해서 그들이 이해한것들을 받아들여야 합니다. 감정을 인정하고 존중하는것이 중요합니다. 그리고 격력한 감정폭발에 대해서도 준비되어 있어야 합니다.\n\n"+
                        "4. 일반적 보살핌과 현실적 도움을 제공하라.\n"+
                        "위기 상황에 처하면 다른 사람들이 현실적인 도움을 주는 것이 큰 도움이 됩니다. 또한 현실적 도움은 보살핌과 공감을 표현하는 수단이기도 합니다."



        );







        firstAidMap.put("심장발작","1. 지속적으로 심한 가슴통증 또는 설명할 수 없는 팔, 목, 턱, 등 또는 상복부의 불편감이 있다.\n" +
                "2. 즉시 119에 신고하거나, 다른 사람에게 신고를 부타한다.\n" +
                "3. 환자에게 바닥에 앉거나, 벽 또는 의자에 기대는 편안한 자세를 취하게 한다.\n"+
                "4. 119구급대를 기다리는 동안 환자를 계속 안심시켜 준다."
        );


        firstAidMap.put("쏘임/물림",
                "벌에 쏘였을때)\n\n"+"침이 박힌 피부주위를 딱딱한 카드 등으로 눌러서 침을 제거한다.\n" +
                "통증을 해소하고 부기를 막기 위해서는 찬 찜질을 한다.\n" +
                "통증이나 부기가 계속되면 병원으로 이송한다.\n\n\n" +
                "개에 물렸을때)\n\n"+
                        "광견병이 있을 수 있으므로 집에서 기르는 개나 고양이에게 물리면 일단 그 동물을 가둬두고 10일 동안 관찰한다.\n" +
                "비눗물로 상처를 깨끗이 씻고 압력이 약한 물로 즉시 헹군다.\n" +
                "지혈을 하고 상처를 치료한다.\n" +
                "의사의 치료를 받고 필요시 광견병 예방 주사를 맞는다."
        );


        firstAidMap.put("알레르기/아나필락시스","1. 만약 환자가 알레르기가 있고 처방받은 약을 가지고 있다면 약을 복용하도록 도와준다.\n"+
        "2. 119구급대가 도착할 때까지 계속 안심시켜준다.");

        firstAidMap.put("열사병","환자를 시원한 장소로 옮기고 옷을 벗긴 뒤 반좌위로 하고 머리를 높입니다.\n" +
                "찬 물수건으로 씻겨주던가 찬물에 몸을 담그게 하여 체온을 신속히 냉각시킵니다");

//        firstAidMap.put("저체온증","환자를 즉시 병원으로 이송합니다.\n" +
//                "환자의 움직임을 최소화한다. 사소한 충격에도 심실세동이 유발될 수 있습니다.\n" +
//                "심폐소생술이 어려운 현장에서는 갑작스럽게 체온을 정상으로 되돌리려는 시도는 하지 않도록 하며 더 이상의 체온강하를 방지하기 위한 처치만 합니다. 젖은 의복을 제거하고 담요를 덮어주며 실내를 따뜻하게 해 줍니다.\n" +
//                "저체온 상태에서는 외형상으로는 죽은 것처럼 보이는 환자가 응급처치로 다시 소생하는 경우가 많기 때문에 사망한 것을 추정해서는 안됩니다.\n" +
//                "실온의 산소를 투여합니다.\n" +
//                "언제라도 심폐소생술이 가능하도록 준비합니다\n\n\n"+
//                "※ 의식이 있는 경미한 저체온 환자의 응급처치\n\n" +
//                "환자가 괜찮아 보이고 혼자 힘으로욕탕에 들어갈 정도이면 40℃의 욕탕에서 몸을 덮여도 됩니다.\n" +
//                "연약하거나 늙은 환자는 서서히 덮히도록 합니다. 담요로 온 몸을 덮어주고 환자의 머리도 또 다른 더운 것으로 감싸줍니다.\n" +
//                "의식이 있는 환자에게는 뜨거운 음료, 스프 또는 쵸코렛 같은 고열량 음식을 먹이도록 합니다.");

        firstAidMap.put("저체온증 - 경미할 경우","환자가 괜찮아 보이고 혼자 힘으로욕탕에 들어갈 정도이면 40℃의 욕탕에서 몸을 덮여도 됩니다.\n" +
                "연약하거나 늙은 환자는 서서히 덮히도록 합니다. 담요로 온 몸을 덮어주고 환자의 머리도 또 다른 더운 것으로 감싸줍니다.\n" +
                "의식이 있는 환자에게는 뜨거운 음료, 스프 또는 쵸코렛 같은 고열량 음식을 먹이도록 합니다.");
        firstAidMap.put("저체온증 - 위급할 경우","환자를 즉시 병원으로 이송합니다.\n" +
                "환자의 움직임을 최소화한다. 사소한 충격에도 심실세동이 유발될 수 있습니다.\n" +
                "심폐소생술이 어려운 현장에서는 갑작스럽게 체온을 정상으로 되돌리려는 시도는 하지 않도록 하며 더 이상의 체온강하를 방지하기 위한 처치만 합니다. 젖은 의복을 제거하고 담요를 덮어주며 실내를 따뜻하게 해 줍니다.\n" +
                "저체온 상태에서는 외형상으로는 죽은 것처럼 보이는 환자가 응급처치로 다시 소생하는 경우가 많기 때문에 사망한 것을 추정해서는 안됩니다.\n" +
                "실온의 산소를 투여합니다.\n" +
                "언제라도 심폐소생술이 가능하도록 준비합니다\n\n\n");


        firstAidMap.put("정신적 고통","1. 당신이 귀 기울이고 있다는 것을 보여주고, 어떻게 도움을 줬으면 하는지 침착하게 물어본다.\n"+
        "2. 그들 주변에서 일어나고 있는 일이 무엇인지, 필요한 것이 무엇인지를 고려한다.");

        firstAidMap.put("좌상/염좌","1. 손상 부위에 얼음을 올려준다.\n"+
        "2. 만약 상황이 개선되지 않는다면 의사의 진단을 받도록 한다.");



        firstAidMap.put("약물중독", "의식을 확인하고 기도를 유지한다.\n" +
                "만일 환자가 의식이 없으면 호흡과 맥박을 확인한다.\n" +
                "중독물질을 복용하였을 때는 우유와 물을 마시게 하여 중독 물질을 회석시키거나 구토를 유발시킨다.\n" +
                "단, 환자가 의식이 없거나 경련이 있을 경우에는 구토를 시키지 않도록 한다.\n" +
                "중독 가능성이 있는 약물, 화학물질, 약병 등을 수거하여 비닐팩에 담아 병원으로 가져간다.\n\n\n");
        firstAidMap.put("식중독"," 일반적으로 소화기 질환에 의한 증상과 식중독에 의한 증상의 구별이 어렵다.\n" +
                "따라서 119 신고 등 환자를 신속히 병원으로 이송하는 것이 좋다.\n"+
                "단, 환자가 섭취하다가 남은 음식물은 병원으로 반드시 가져가도록 한다");

//        firstAidMap.put("중독/해로운 물질",
//                "약물중독)\n\n"+
//                        "의식을 확인하고 기도를 유지한다.\n" +
//                        "만일 환자가 의식이 없으면 호흡과 맥박을 확인한다.\n" +
//                        "중독물질을 복용하였을 때는 우유와 물을 마시게 하여 중독 물질을 회석시키거나 구토를 유발시킨다.\n" +
//                        "단, 환자가 의식이 없거나 경련이 있을 경우에는 구토를 시키지 않도록 한다.\n" +
//                        "중독 가능성이 있는 약물, 화학물질, 약병 등을 수거하여 비닐팩에 담아 병원으로 가져간다.\n\n\n"+
//                        "식중독)\n\n"+
//                        " 일반적으로 소화기 질환에 의한 증상과 식중독에 의한 증상의 구별이 어렵다.\n" +
//                        "따라서 119 신고 등 환자를 신속히 병원으로 이송하는 것이 좋다.\n"+
//                        "단, 환자가 섭취하다가 남은 음식물은 병원으로 반드시 가져가도록 한다"
//        );


        firstAidMap.put("천식발작","1. 눕히기보다는 앉히는 것이 호흡하기 수월하다.\n"+
        "2. 등을 쓰다듬거나, 불안을 가라앉히도록 이야기를 해주며 안심시킨다. 가족이나 주변 사람들이 당황하면 환자는 더 불안해진다.\n"+
                "3. 실내 온도가 너무 덥거나 추워서는 안된다. 신선한 공기가 있으면 좋지만 창에서 찬바람이 들어오면 더 역효과를 낸다. 자극에 민감한 상태이므로 담배 연기를 비롯해 음식 냄새도 삼가야 한다.\n"+
                "4. 발작 시에는 음식을 먹을 수 있는 상태가 아니다. 천식 발작이 어느 정도 끝나갈 때 준 식사가 원인이 되어 재발하는 경우도 있다. 발작이 끝나고 자연스럽게 먹게 되면 그때 주의해서 음식을 준다."
        );


//        firstAidMap.put("출혈","☞ 상황파악 및 구급대원 현장 도착 전 응급처치\n\n" +
//                "ⓐ 출혈이 심한가? → 출혈부위를 계속해서 손으로 압박한다.\n" +
//                "ⓑ 쇼크증상을 보이는가? → 다리와 발을 지면에서 15～30cm 높게 위치시킨다.(호흡이 나빠지면 일으켜 세운다.)\n" +
//                "ⓒ 신체의 일부분이 절단되었는가? → 절단 부위를 찾는다.\n" +
//                "ⓓ 속이 미식거리거나, 구토를 하는가? → 옆으로 눕힌다.\n" +
//                "ⓔ 신체에 이물질이 박혀 있는가? → 제거하지 않는다.\n\n\n"+
//                "지혈하는 방법)\n\n" +
//                "① 상처를 직접 누르는 방법(직접압박법)\n" +
//                "압박붕대나 손으로 출혈부위를 직접 압박하는 방법입니다.\n\n"+
//                "② 핏줄 누르기(동맥점 압박)\n" +
//                "직접압박으로도 지혈되지 않을 때는 출혈 부위에서 몸통 방향으로 가까이 위치한 동맥부위를 압박합니다.\n 즉, 팔에서 피가 나면 윗팔동맥(상완동맥)을 눌러 피를 멈추게 할 수 있고 다리에서 나는 피는 사타구니 동맥을 눌러 멈추게 할 수 있습니다.\n\n" +
//                "③ 지혈대 이용하기\n" +
//                "여러 가지 방법을 사용해도 효과가 없는 경우, 심하게 나는 피를 멈추게 하기 위해 지혈대를 사용할 수 있으나, 심각한 합병증을 초래할 수 있으므로 주의해야 하며, 지혈대는 생명을 건지기 위한 방법으로 마지막에 사용되는 최후의 방법입니다\n" +
//                "지혈대를 꼭 사용해야 할 경우 폭이 넓고 평평한 것을 사용합니다. 밧줄이나 철사와 같이 폭이 좁은 것은 절대 사용하지 말아야 합니다. 일단 사용한 지혈대는 병원에 도착할 때까지 풀지 않습니다.\n\n\n"+
//                "코의 출혈)\n"+
//                "① 윗입술과 잇몸 사이에 둥글게 말은 거즈를 넣고 코를 손가락으로 눌러 압박을 가하고 환자는 윗입술에 넣은 거즈를 눌러서 지혈에 도움을 주도록 한다.\n" +
//                "② 코피가 폐로 유입되지 않도록 가능한 환자를 앉은 상태에서 머리를 앞으로 숙이도록 한다.\n" +
//                "③ 10분 후에 압박을 풀어주고 피가 멈추지 않으면 10분을 더 압박한다.\n" +
//                "④ 코 위에 얼음물 주머니를 올려주면 지혈에 도움이 된다.\n\n\n"+
//                "귀의 출혈)\n"+
//                "① 피나 액체가 흘러나오도록 손상 받은 쪽으로 귀를 기울인다.\n" +
//                "② 소독 거즈로 귀를 덮고 접착성 테이프로 살짝 붙인다. 이때 귀를 마개로 막거나 혈액이 흐르는 것을 방해하지 않아야 한다."
//      );


        firstAidMap.put("화재에 의한 화상","옷이나 몸에 불이 붙으면 멈춰 바로 바닥에 쓰러져서 몸을 빠르게 뒹굴면서 불을 진화합니다.\n" +
                "수도물이나 흐르는 물등으로 통증이 가실 때까지 화상부위를 담근 채 식힙니다.\n" +
                "(주의) 화상부위를 찬물에 10분 이상 담그면 체온의 손실을 초래하여 저체온증을 유발할 수 있습니다.\n\n\n");
        firstAidMap.put("경화상(1도, 2도)","물집은 터트리지 마십시오.\n" +
                "소독된 약용 바세린이나 화상연고를 바르고 드레싱을 합니다.\n" +
                "(주의) 화상부위를 찬물에 10분 이상 담그면 체온의 손실을 초래하여 저체온증을 유발할 수 있습니다.\n\n\n");
        firstAidMap.put("중화상(3도)","충격과 감염을 방지합니다.\n" +
                "체온을 유지시킵니다.\n" +
                "타서 몸에 붙은 의복을 억지로 떼려 하지 말고 그 외의 의복부분을 가위로 절단합니다.\n" +
                "깨끗한 헝겊을 소다수 또는 식염수에 적시어 댑니다.\n" +
                "물을 조금씩 천천히 먹입니다.\n" +
                "더러운 물건이나 먼지가 화상부위에 닿지 않게 주의합니다.\n" +
                "호흡곤란이 있는 환자에게는 신속히 산소를 투여합니다.");

//        firstAidMap.put("화상",
//                "가. 화재에 의한 화상\n\n" +
//                "옷이나 몸에 불이 붙으면 멈춰 바로 바닥에 쓰러져서 몸을 빠르게 뒹굴면서 불을 진화합니다.\n" +
//                "수도물이나 흐르는 물등으로 통증이 가실 때까지 화상부위를 담근 채 식힙니다.\n" +
//                "(주의) 화상부위를 찬물에 10분 이상 담그면 체온의 손실을 초래하여 저체온증을 유발할 수 있습니다.\n\n\n" +
//                "나. 경화상(1도, 2도 화상)\n\n" +
//                "물집은 터트리지 마십시오.\n" +
//                "소독된 약용 바세린이나 화상연고를 바르고 드레싱을 합니다.\n" +
//                "(주의) 화상부위를 찬물에 10분 이상 담그면 체온의 손실을 초래하여 저체온증을 유발할 수 있습니다.\n\n\n" +
//                "다. 중화상(3도 화상)\n\n" +
//                "충격과 감염을 방지합니다.\n" +
//                "체온을 유지시킵니다.\n" +
//                "타서 몸에 붙은 의복을 억지로 떼려 하지 말고 그 외의 의복부분을 가위로 절단합니다.\n" +
//                "깨끗한 헝겊을 소다수 또는 식염수에 적시어 댑니다.\n" +
//                "물을 조금씩 천천히 먹입니다.\n" +
//                "더러운 물건이나 먼지가 화상부위에 닿지 않게 주의합니다.\n" +
//                "호흡곤란이 있는 환자에게는 신속히 산소를 투여합니다.");
    }

}
