<?xml version="1.0" encoding="UTF-8"?>
<html>
<head>
    <title>生成PDF合同</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <style type="text/css">
        body {
            font-family: SimSun;
            font-size:22px;
        }

        span{
            word-wrap: break-word;
            word-break: normal;
        }

        .underline{
            padding-bottom:2px;
            padding-top: 2px;
            border-bottom:0.5px solid #000;
        }
        .label{
            padding-top: 3px;
        }
    </style>
</head>
<body>
<p align="center">借款协议书</p><br></br>

<div>
    <div style="font-size:14px;font-weight:800;">
        <div>甲方（出借人）：<span class="underline">${map.realname1!}</span></div>
        <br></br>

        <div>身份证号/工商登记号：<span class="underline">${map.idcard1!}</span></div>
        <br></br>

        <div>借款人（乙方）：<span class="underline">${map.realname2!}</span>[平台借款人]</div>
        <div class="label">身份证号/工商登记号：<span class="underline">${map.idcard2!}</span></div>
        <br></br>

        <div> 丙方（服务方）：<span class="underline">杭州拓道互联网金融服务有限公司</span></div>
    </div>
    <br></br>
</div>

<div style="font-size:16px;padding-bottom:12px;">鉴于：</div>

<div style="margin-top: 30px;">一.甲方的权利和义务</div>
    <table  style="font-size:14px;" >
        <tr>
            <td>
                <span style="padding-left:30px;display:block;">
                    1.丙方系一家合法成立并有效存续的互联网金融公司，拥有www.51tuodao.com网站（下称统称为“拓道金<br></br> </span>
                    <span style="padding-left:45px;display:block;">服平台”）的经营权。丙方为借贷双方提供专业的信息咨询服务。<br></br> </span>
            </td>
        </tr>
        <tr>
            <td>
                <span style="padding-left:30px;display:block;">2.甲方和乙方系根据中华人民共和国法律成立并存续的企业，或是居住在中华人民共和国境内、符合中华人<br></br></span>
                    <span style="padding-left:45px;display:block;">民共和国法律规定的具有完全民事行为能力的自然人。 </span>
            </td>
        </tr>
        <tr>
            <td>
                <span style="padding-left:30px;display:block;"> 3.甲方和乙方均已在丙方旗下的“拓道金服平台”注册，并承诺其提供给丙方的信息是完全真实的。甲方<br></br> </span>
                <span style="padding-left:45px;display:block;">的注册账号为<span class="underline" id="username1">${map.username1!}</span>，乙方的注册账号为 <span class="underline" id="username2">${map.username2!}</span> 。  </span>
            </td>
        </tr>
        <tr>
            <td>
                <span style="padding-left:30px;display:block;">4.甲方承诺对本协议涉及的借款资金具有完全的支配能力，是其自有闲散资金，为其合法所得。<br></br></span>
                <span style="padding-left:30px;display:block;"> 5.乙方有借款需求，甲方亦同意借款，双方有意成立借贷关系。现甲、乙、丙三方本着平等、自愿、诚实<br></br></span>
                <span style="padding-left:45px;display:block;">守信的原则，经三方协商一致，达成如下协议，以资共同遵照履行：<br></br></span>
            </td>
        </tr>
    </table>
<br></br>
<div>一.借款基本信息</div>
<table  style="font-size:14px;" >
    <tr>
        <td>
                <span style="padding-left:30px;display:block;">
                   甲方与丙方平台其他注册用户通过丙方平台共同投资标号为 <span style="border-bottom:1px solid #000;">${map.borrowId!}</span>，汽车融资标（具体详见标的信息）。<br></br>
                    乙方借款本金总额人民币<span class="underline">${map.account!}元</span>及利息<span class="underline">${map.interest!}元</span>，
                    其中甲方向乙方出借人民币<span class="underline">${map.userAccount!}元</span>。
                    借款期限为<span class="underline">${map.beforeDate!}</span>至<span class="underline">${map.afterDate!}</span>止。<br></br>
                    借款利息<span class="underline">${map.userInterest!}元</span>，计算自<span class="underline">${map.beforeDate!}</span>至  <span class="underline">${map.afterDate!}</span>止，计<span class="underline">${map.borrowPeriod!}</span> 。
                    <br></br>
                </span>
        </td>
    </tr>
</table>

<br></br>
<div>二.协议的订立及支付</div>
<table style="font-size:14px;">
    <tr>
        <td>
             <span style="padding-left:30px;display:block;">1.各方同意并确认，各方自行或授权其代理人根据“拓道金服平台”有关规则和说明，通过在“拓道金服平<br></br></span>
                <span style="padding-left:45px;display:block;">  台”点击“拓道金服平台”相关按钮进行借款申请和投标操作等方式确认签署本协议。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
             <span style="padding-left:30px;display:block;">2.各方通过上述方式签署本协议且丙方审核通过时，本协议立即成立；甲方不可撤销地授权丙方在本协议<br></br></span>
            <span style="padding-left:45px;display:block;">成立的同时将出借款项在扣除相关费用后划转、支付给乙方。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
             <span style="padding-left:30px;display:block;">3.甲方同意并授权丙方按本协议及“拓道金服平台”有关规则的约定对出借款项和相关费用进行划扣和支<br></br></span>
             <span style="padding-left:45px;display:block;">付，并由丙方代为收取乙方支付的借款本息及有关费用。<br></br></span>
        </td>
    </tr>
</table>
<br></br>

<div>三.借款的发放与归还</div>
<table style="font-size:14px;">
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">1.放款方式：本协议生效的同时，甲方即不可撤销的授权丙方委托相应的第三方支付机构或监管银行等合作机构，<br></br></span>
            <span style="padding-left:45px;display:block;">  将本协议项下甲方出借款划转至乙方的银行收款账户或乙方指定的银行收款账户。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">2.还款方式：甲、乙双方约定还款方式为乙方按月支付等额借款本息至甲方授权的丙方指定还款账户。<br></br></span>
            <span style="padding-left:45px;display:block;">计算公式：月还款=本金*月利息*（1+月利率）^借款期限/（1+月利率）^借款期限-1。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">3.乙方可以提前还款，若乙方于当期付息日前15天（包括15天）内还款的，由其向甲方支付半个月的利<br></br></span>
            <span style="padding-left:45px;display:block;">息作为违约金；若乙方于当期付息日前15天以外还款的，由其向甲方支付一个月的利息作为违约金。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">4.甲方的“拓道金服平台”账户收到全部借款本息时视为协议履行完毕。若因银行原因造成资金到账延迟的，<br></br></span>
            <span style="padding-left:45px;display:block;">丙方不承担任何责任。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">5.付息日是指：“拓道金服平台”应支付投资人出借金额所得收益的日期。<br></br></span>
        </td>
    </tr>
</table>
<br></br>

<div>四.各方的权利与义务</div>
<table style="font-size:14px;">
    <tr>
        <td>
            <span style="padding-left:30px;display:block;font-size: 14px;"><b> 1.甲方的权利义务</b><br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;"> 1.1.甲方应依本合同约定按时足额向乙方发放借款（因乙方原因造成迟延的除外）；</span>
        </td>
    </tr>
    <tr>
        <td>
             <span style="padding-left:45px;display:block;">1.2.甲方保证其所用于出借的资金来源合法，甲方是该资金的合法所有人，如果第三人对资金归属、<br></br></span>
              <span style="padding-left:70px;display:block;">合法性问题发生争议，由甲方自行解决并承担相关责任。在该等争议解决（以取得生效的判决、裁决<br></br></span>
              <span style="padding-left:70px;display:block;">或行政命令、通知为标志）之前，丙方有权根据政府机关、司法机关或仲裁机构出具且生效的法律文<br></br></span>
             <span style="padding-left:70px;display:block;">件拒绝向甲方支付丙方代其领受的相关款项。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;"> 1.3.甲方有权依本合同约定或法律规定，收取乙方应偿付的借款本金、利息、违约金及所有其他应付费用</span>
            <span style="padding-left:70px;display:block;">。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
             <span style="padding-left:45px;display:block;">1.4.甲方有权将本协议项下对乙方的部分或全部债权，根据自己的意愿转让给第三人，且无需经乙方的<br></br></span>
             <span style="padding-left:70px;display:block;">同意。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">1.5.如乙方违约，甲方有权要求丙方提供其已获得的乙方信息。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">1.6.甲方承诺对依据本协议获得的乙方信息或资料予以保密，除用于本协议目的进行出借与合理催收外<br></br></span>
            <span style="padding-left:70px;display:block;">，不得向外转让或披露。<br></br></span>

        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;"> 2. 乙方权利和义务<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;"> 2.1.乙方有权要求甲方按本合同约定发放借款；</span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.2.乙方必须按期足额向甲方归还每期应还利息和本金，向丙方按期足额支付相关借款管理费和居间服<br></br></span>
            <span style="padding-left:70px;display:block;">务费，并支付与乙方逾期或提前还款有关的费用。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.3.乙方有权在借款到期日前提前偿还全部本金；除本合同另有约定外，在提前偿还全部本金的情况下，<br></br></span>
            <span style="padding-left:70px;display:block;">乙方应支付的利息计到提前还款日。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.4.除按照本协议约定进行使用外，乙方不得将本协议项下之借款用于其他任何用途。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.5.乙方确认甲方可以根据自己的意愿对其根据本协议形成的对乙方的部分或全部债权进行转让，并特<br></br></span>
             <span style="padding-left:70px;display:block;">此同意甲方届时转让债权无需事前通知乙方；债权转让完成时，该等转让信息将在乙方“拓道金服平<br></br></span>
             <span style="padding-left:70px;display:block;">台”账户显示；该等债权转让通知一经在乙方“拓道金服平台”账户显示，即视为甲方已将债权转让<br></br></span>
             <span style="padding-left:70px;display:block;">事宜通知乙方，乙方不得以任何理由对此提出异议或抗辩。同时乙方承诺将采取必要措施配合债权一<br></br></span>
             <span style="padding-left:70px;display:block;">的次或多次转让或届时债权人债权的实现。在债权转让后，甲方在本协议项下的权利和义务全部自动<br></br></span>
             <span style="padding-left:70px;display:block;">转移到债权受让人名下，乙方应按照本协议的约定向债权受让人支付每期应还借款本息。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.6.乙方应确保其提供的信息和资料的真实性和合法性，不存在虚假信息或隐瞒。若乙方违反本协议任<br></br></span>
             <span style="padding-left:70px;display:block;">何约定，丙方有权根据本协议及有关条款对乙方的信息和资料予以合理的披露。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.7.乙方同意，甲方和丙方有权使用其自行收集或乙方提供的乙方资料和信息用于包括但不限于如下用<br></br></span>
            <span style="padding-left:70px;display:block;">途：<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:70px;display:block;">（1）为了提供本协议项下的服务，丙方向有关的合作机构提供必要之资料；<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:70px;display:block;">（2）用于解决争议、对纠纷进行调停等；<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:70px;display:block;">（3）本协议或法律法规规定的其他用途。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.8.乙方不得将本协议项下的任何权利义务转让给任何其他方。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.9.发生对其履行本合同项下还款义务产生不利影响的其他事件，包括但不限于财务状况恶化、婚姻状<br></br></span>
            <span style="padding-left:70px;display:block;">况变化、陷入经济纠纷、辞职、辞退、开除、除名、重大危机事件时，乙方应于事件发生后三日内书<br></br></span>
            <span style="padding-left:70px;display:block;">面通知甲方和丙方。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">2.10.乙方有权了解其在丙方的信用评审进度及结果。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;"> 3. 丙方的权利和义务<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;"> 3.1.丙方有权根据乙方提供的各项信息及丙方独立获得的信息评定乙方在丙方处所拥有的个人信用等级，<br></br></span>
            <span style="padding-left:70px;display:block;">并根据对乙方个人信息的评审结果，决定是否审核通过并将乙方的借款需求向甲方进行推荐。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">3.2.甲方在此同意并确认：向乙方出借相应款项时，委托丙方在本协议成立后将该笔借款直接划付至乙<br></br></span>
            <span style="padding-left:70px;display:block;">方账户。甲方授权并委托丙方代其收取本协议文首所约定的出借人每月应收本息，代收后划付至甲方<br></br></span>
            <span style="padding-left:70px;display:block;">的“拓道金服平台”账户，乙方对此表示认可。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">3.3.丙方有权就为本协议借款所提供的服务向乙方收取借款管理费和/或居间服务费及本协议约定的他费</span>
            <span style="padding-left:70px;display:block;">用。<br></br></span>

        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">3.4.甲乙双方同意丙方有权代甲方在必要时对乙方进行借款的违约提醒及催收工作，包括但不限于电话通知、上门通<br></br></span>
            <span style="padding-left:70px;display:block;">知、发律师函、对乙方提起诉讼等。甲方在此确认委托丙方为其进行以上工作，并授权丙方可以将此工作委托给其<br></br></span>
            <span style="padding-left:70px;display:block;">他方进行。乙方对前述委托的提醒、催收事项已明确知晓并应积极配合，同时应承担因乙方逾期还款而产生的催收<br></br></span>
            <span style="padding-left:70px;display:block;">费用。<br></br></span>

        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">3.5.丙方接受甲乙双方的委托行为所产生的法律后果由相应委托方承担。如因乙方或甲方或其他非因丙<br></br></span>
            <span style="padding-left:70px;display:block;">方所能预见/控制的原因（包括但不限于技术问题）造成的延误或错误，丙方不承担任何责任。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:45px;display:block;">3.6.丙方应对甲方和乙方的信息保密；但发生本协议其他条款约定的丙方可以进行披露的情形，或相关<br></br></span>
            <span style="padding-left:70px;display:block;">权力部门要求（包括但不限于法院、仲裁机构、金融监管机构等）披露的，丙方有权披露。<br></br></span>
        </td>
    </tr>
</table>
<br></br>

<div>五.居间服务费等费用</div>
<table style="font-size:14px;">
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">1.对于丙方向乙方提供的撮合本协议项下借款交易达成之信用评估和居间服务，乙方同意：本协议生效<br></br></span>
            <span style="padding-left:45px;display:block;"> 时，丙方有权一次性收取居间服务费，具体详见“拓道金服平台”相关费用说明。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">2.乙方提前清偿甲方借款的，丙方已经收取的居间服务费和借款管理费不予退还；<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">3.乙方未按约定按时、足额向丙方支付居间服务费和借款管理费的，应承担每日千分之一的违约金，直<br></br></span>
            <span style="padding-left:45px;display:block;">至丙方收到全额服务费为止。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">4.如乙方和丙方协商一致调整借款管理费和居间服务费时，无需经过甲方同意。<br></br></span>
        </td>
    </tr>
</table>
<br></br>

<div>六.违约责任</div>
<table style="font-size:14px;">
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">1.乙方发生违约情形，丙方有权将乙方的“逾期记录”、“恶意行为”或“不良情况”提供给中国人民银行<br></br></span>
            <span style="padding-left:45px;display:block;">个人信用信息基础数据库及信贷征信主管部门批准建立的个人信用数据库，以供有关单位、部门或个人依法<br></br></span>
            <span style="padding-left:45px;display:block;">查询和使用；<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">2.  丙方有权将乙方违约失信的相关信息及乙方其他信息向包括但不限于媒体、用人单位、公安机关、检察<br></br></span>
             <span style="padding-left:45px;display:block;">机关、法律机关及有关逾期款项催收服务机构披露。对此丙方不承担任何责任；<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">3.丙方有权以各种通讯手段(包括但不限于：电话、短信、微信等各种通讯手段)告知乙方的近亲属、朋友乙<br></br></span>
            <span style="padding-left:45px;display:block;">方的欠款信息，并在丙方网站或其他报纸、网站上发布乙方的欠款信息，信息内容包括但不限于乙方的<br></br></span>
            <span style="padding-left:45px;display:block;">姓名、身份证号码、住址、工作单位、照片、欠款金额、逾期时间及违约责任等。<br></br></span>
        </td>
    </tr>
</table>
<br></br>

<div>六.附则</div>
<table style="font-size:14px;">
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">1.本协议通过“拓道金服平台”以电子文本形式生成，可以有一份或者多份且每一份具有同等法律效力，<br></br></span>
            <span style="padding-left:45px;display:block;">并永久保存在“拓道金服平台”为此设立的专用服务器上备查和保管。双方均认可该形式的协议效力。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">2.  甲乙双方同意并确认，双方通过银行账户、“拓道金服平台”账户等采取的行为所产生的法律效果和法<br></br></span>
            <span style="padding-left:45px;display:block;">律责任归属于甲方、乙方本人。甲乙双方授权和委托丙方根据本协议所采取的全部行动和措施的法律后<br></br></span>
            <span style="padding-left:45px;display:block;">果均归属于甲方、乙方本人，与丙方无关，丙方也不因此承担任何责任。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">3.甲乙双方确认并同意，委托丙方对本协议项下的任何金额进行计算，在无明显错误的情况下，甲乙双方<br></br></span>
            <span style="padding-left:45px;display:block;">认可丙方对本协议项下任何金额的任何证明或确定。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">4.本协议各方之间的书面通知或文件往来可以采用以下任何一种方式送达：1）、邮件送达方式，发送人以<br></br></span>
            <span style="padding-left:45px;display:block;">协议载明邮箱发出邮件即视为送达。2）快递送达方式，以协议载明各方地址为准，若各方地址变更未<br></br></span>
            <span style="padding-left:45px;display:block;">履行通知义务的，则视为送达；3）传真送达方式，以协议载明传真号为准；<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">5.如本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议<br></br></span>
            <span style="padding-left:45px;display:block;">其他条款的效力。<br></br></span>

        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">6.本协议项下的附件和补充协议构成本协议不可分割的一部分。<br></br></span>
        </td>
    </tr>
    <tr>
        <td>
            <span style="padding-left:30px;display:block;">7.甲乙双方确认，本协议的签约地址为杭州市西湖区。若发生争议，双方无法达成一致意见，任一方可选<br></br></span>
            <span style="padding-left:45px;display:block;">择协议签订地法院提起诉讼。<br></br></span>
        </td>
    </tr>

</table>
<br></br>
</body>
</html>