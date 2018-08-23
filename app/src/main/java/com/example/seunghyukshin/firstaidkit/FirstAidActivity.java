package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    HashMap<String,String> firstAidMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        TextView_name = (TextView) findViewById(R.id.NameText);
        TextView_contents = (TextView) findViewById(R.id.ContentsText);
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
        firstAidMap.put("골절","척추 골절)\n 환자를 움직이지 말고 손으로 머리를 고정하라! \n 감은 옷을 따로 대어 환자를 지지하라!\n\n"+
                "팔의 골절)\n 상처 입은 팔을 가슴에 대고 가슴과 팔 지지하라!\n 가슴과 팔 사이에 부드러운 헝겊 조각 같은 것을 끼워 주라!\n\n "+
                "골반 골절)\n 다리를 펴준 채로 환자를 눕히거나 무릎을 구부리는 것이 더 편안하다고 하면 무릎 밑에 담요를 말아서 대고 다리를 묶어서 고정하는데 관절 사이에는 패드를 넣어라! \n\n "+
                "발의 골절)\n 아픈 부위의 발을 들고 발바닥에 헝겊을 대고 부목을 받쳐준 후 고정하라!\n\n "+
                "쇄골 골절)\n 환자를 앉히고, 손상된 쪽 팔을 가슴을 지나 반대쪽으로 가게 하라! \n 넓은 천으로 다친 쪽 팔을 가슴에 고정시켜라! (삼각건으로 고정)"

        );


        firstAidMap.put("기도폐쇄","1. 다 막히지 않은 경우 (일부만 막힌 경우)\n\n"+
                "숨이 통하는 경우에는 계속 기침을 하도록 유도하며, 계속해서 기침을 해도 이물질이 배출되지 않을 때에는 즉시 119로 연락을 하여 도움을 요청합니다.\n\n\n" +
                "2.꽉 막힌 경우\n\n"+"① 정신을 잃지 않고, 숨길이 막힌 환자의 처치법(복부 밀쳐 올리기)\n\n" +
                "환자가 정신을 잃지 않았다면 당신은 환자의 뒤로 돌아가서, 환자의 허리를 껴안습니다.\n" +
                " 당신의 한 손은 주먹을 쥐어 환자의 상복부, 즉 배꼽과 명치끝의 중간 부분에 대고 다른 손으로는 주먹 쥔 손을 감싼 후에 환자의 복부를 당신의 가슴 쪽으로 강하게 끌어올리는 방법입니다.\n 5회 연속 압박을 실행한 후에는 환자의 입안을 살피고, 입안에서 이물질이 발견되면 제거하고, 이물질이 관찰되지 않으면 다시 복부 밀쳐 올리기를 5회 시행합니다.\n\n\n" +
                "② 의식이 없는 환자의 완전 기도폐쇄 처치법\n\n" +
                "쓰러진 환자를 바로 눕힙니다. 당신은 환자의 옆에 무릎을 대고 앉아서, 한 손으로는 주먹을 쥐어 환자의 상복부, 즉 배꼽과 명치끝의 중간 부분에 대고 다른 손으로는 주먹 쥔 손을 감싼 후에 환자의 복부를 환자의 가슴 쪽과 등 쪽으로 강하게 쳐 올리는 방법입니다.\n 5회 연속 압박을 실행한 후에는 환자의 입안을 살피고, 입안에서 이물질이 발견되면 제거하고, 이물질이 관찰되지 않으면 다시 복부 밀쳐 올리기를 5회 시행합니다."


        );


        firstAidMap.put("뇌수막염","");
        firstAidMap.put("뇌졸중","");
        firstAidMap.put("당뇨 응급상황","");
        firstAidMap.put("머리손상","");


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


        firstAidMap.put("발작/간질","");
        firstAidMap.put("심리적 응급처치","");
        firstAidMap.put("심장발작","");
        firstAidMap.put("쏘임/물림","");
        firstAidMap.put("알레르기/아나필락시스","");
        firstAidMap.put("열사병","");
        firstAidMap.put("저체온증","");
        firstAidMap.put("정신적 고통","");
        firstAidMap.put("좌상/염좌","");
        firstAidMap.put("중독/해로운 물질","");
        firstAidMap.put("천식발작","");


        firstAidMap.put("출혈","☞ 상황파악 및 구급대원 현장 도착 전 응급처치\n\n" +
                "ⓐ 출혈이 심한가? → 출혈부위를 계속해서 손으로 압박한다.\n" +
                "ⓑ 쇼크증상을 보이는가? → 다리와 발을 지면에서 15～30cm 높게 위치시킨다.(호흡이 나빠지면 일으켜 세운다.)\n" +
                "ⓒ 신체의 일부분이 절단되었는가? → 절단 부위를 찾는다.\n" +
                "ⓓ 속이 미식거리거나, 구토를 하는가? → 옆으로 눕힌다.\n" +
                "ⓔ 신체에 이물질이 박혀 있는가? → 제거하지 않는다.\n\n\n"+
                "지혈하는 방법)\n\n" +
                "① 상처를 직접 누르는 방법(직접압박법)\n" +
                "압박붕대나 손으로 출혈부위를 직접 압박하는 방법입니다.\n\n"+
                "② 핏줄 누르기(동맥점 압박)\n" +
                "직접압박으로도 지혈되지 않을 때는 출혈 부위에서 몸통 방향으로 가까이 위치한 동맥부위를 압박합니다.\n 즉, 팔에서 피가 나면 윗팔동맥(상완동맥)을 눌러 피를 멈추게 할 수 있고 다리에서 나는 피는 사타구니 동맥을 눌러 멈추게 할 수 있습니다.\n\n" +
                "③ 지혈대 이용하기\n" +
                "여러 가지 방법을 사용해도 효과가 없는 경우, 심하게 나는 피를 멈추게 하기 위해 지혈대를 사용할 수 있으나, 심각한 합병증을 초래할 수 있으므로 주의해야 하며, 지혈대는 생명을 건지기 위한 방법으로 마지막에 사용되는 최후의 방법입니다\n" +
                "지혈대를 꼭 사용해야 할 경우 폭이 넓고 평평한 것을 사용합니다. 밧줄이나 철사와 같이 폭이 좁은 것은 절대 사용하지 말아야 합니다. 일단 사용한 지혈대는 병원에 도착할 때까지 풀지 않습니다.\n\n\n"+
                "코의 출혈)\n"+
                "① 윗입술과 잇몸 사이에 둥글게 말은 거즈를 넣고 코를 손가락으로 눌러 압박을 가하고 환자는 윗입술에 넣은 거즈를 눌러서 지혈에 도움을 주도록 한다.\n" +
                "② 코피가 폐로 유입되지 않도록 가능한 환자를 앉은 상태에서 머리를 앞으로 숙이도록 한다.\n" +
                "③ 10분 후에 압박을 풀어주고 피가 멈추지 않으면 10분을 더 압박한다.\n" +
                "④ 코 위에 얼음물 주머니를 올려주면 지혈에 도움이 된다.\n\n\n"+
                "귀의 출혈)\n"+
                "① 피나 액체가 흘러나오도록 손상 받은 쪽으로 귀를 기울인다.\n" +
                "② 소독 거즈로 귀를 덮고 접착성 테이프로 살짝 붙인다. 이때 귀를 마개로 막거나 혈액이 흐르는 것을 방해하지 않아야 한다."



        );


        firstAidMap.put("화상","");
    }

}
