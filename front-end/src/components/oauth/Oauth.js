import React from "react";
import oauthImage from "../../assets/img/oauth.PNG";
import oauthImage2 from "../../assets/img/oauthImage2.png";
import styles from "./Oauth.module.css";
import exImage1 from "../../assets/img/oauth2.PNG";
import exImage2 from "../../assets/img/oauth3.PNG";
import exImage3 from "../../assets/img/oauth4.PNG";
import exImage4 from "../../assets/img/AddRedirectURI.png";

const OauthExplain = () => {
	return (
		<div className={styles.backBody}>
			<h1 className={styles.oAuthTitle}>SSAFY FINANCE OPEN API 인증 과정</h1>

			<div className={styles.titleExplain}>
				마이데이터를 사용하기 위해선 access-token을 발급 받아야합니다. access-token 발급 과정을
				나타낸 시퀀스 다이어그램입니다.
			</div>

			<img src={oauthImage2} alt="oauth" className={styles.imageContainer} />

			<div className={`${styles.text} ${styles.step0Title}`}>
				<h2>Step0: Redirect URI 등록 </h2>
			</div>
			<div className={styles.step0Explain}>
				금융 더미데이터(마이데이터) 사용을 위해 Redirect URI를 등록하여 client-id와 secret-id 발급
				받아야 합니다.
				<br />
				<br />
				마이페이지&gt;RedirectURI 추가하기를 해주세요.
				<br />
				추가된 RedirectURI에 할당된 client-id와 secret-id를 확인할 수 있습니다.
			</div>

			{/* <img src={exImage4} alt="oauth" className={styles.imageContainer} /> */}

			<div className={styles.text}>
				<h2>Step1: 인가 코드 받기</h2>
				<div className={styles.step1Explain}>
					1. 서비스 서버가 SSAFY FINANCE 인증 서버로 인가 코드 받기를 요청합니다.
					<br />
					2. SSAFY FINANCE 인증 서버가 사용자에게 이메일을 통한 인증을 요구합니다.
					<br />
					3. 사용자는 이메일로 인증을 해야 합니다. <br />
					4. 이메일 인증을 하면 인가를 위한 사용자의 마이데이터 동의를 요청합니다. <br />
					5. SSAFY FINANCE 인증 서버가 서비스 클라이언트가 이전에 등록한 Redirect URI로 인가 코드를
					전달합니다.
				</div>
				<div className={styles.imgs}>
					<div className={styles.eximage}>
						<img src={exImage1}></img>
					</div>
					<div className={styles.eximage}>
						<img src={exImage3}></img>
					</div>
				</div>
				<p>
					요청 <span>&nbsp;</span>
					<strong>GET</strong> <span>&nbsp;</span>https://j9b309.p.ssafy.io/oauth/authorize
				</p>
			</div>

			<table className={styles.table}>
				<thead>
					<tr>
						<th>parameter</th>
						<th>필수 여부</th>
						<th>설명</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>response_type</td>
						<td>Y</td>
						<td>code로 고정</td>
					</tr>

					<tr>
						<td>client_id</td>
						<td>Y</td>
						<td>마이 페이지에 redirect_uri를 등록한 후 생성된 client_id를 넣어야 한다.</td>
					</tr>
					<tr>
						<td>redirect_uri</td>
						<td>Y</td>
						<td>마이 페이지에 등록한 redirect_uri를 넣어야 한다.</td>
					</tr>
					<tr>
						<td>scope</td>
						<td>Y</td>
						<td>read로 고정</td>
					</tr>
				</tbody>
			</table>

			<div className={styles.text2}>
				<h3>요청 예시</h3>
			</div>
			<pre id="json" className={styles.code}>
				https://j9b309.p.ssafy.io/oauth/authorize?response_type=code&client_id={"${client_id}"}
				&redirect_uri=
				{"${redirect_uri}"}&scope=read
			</pre>

			<div className={styles.text2}>
				<h3>응답 예시</h3>
			</div>
			<pre id="json" className={styles.code}>
				HTTP/1.1 302 Found<br></br>
				Location: {"${REDIRECT_URI}?code=${authorize_code}"}
			</pre>
			<div className={styles.text}>
				<h2>Step2: 토큰 받기</h2>

				<div className={styles.step1Explain}>
					1. Step1 에서 받은 인가 코드로 토큰(access-toekn) 발급을 요청합니다.
					<br />
					2. 필수 파라미터를 포함해 POST로 요청합니다. 요청 성공 시 응답은 토큰과 토큰 정보를
					포함합니다.
					<br />
					3. 액세스 토큰으로 마이데이터를 호출할 수 있습니다. 헤더에 Authorization : Bearer
					access-token을 담아 요청합니다.
				</div>
				<p>
					요청 <span>&nbsp;</span> <strong>POST</strong> <span>&nbsp;</span>
					https://j9b309.p.ssafy.io/oauth/token
				</p>
			</div>

			<table className={styles.table}>
				<thead>
					<tr>
						<th>header</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Authorization</td>
						<td>필수</td>
						<td>Basic {"{client_id:client_secret를 Base64로 인코드한 값}"}</td>
					</tr>
				</tbody>
			</table>

			<table className={styles.table}>
				<thead>
					<tr>
						<th>parameter</th>
						<th>필수 여부</th>
						<th>설명</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>grant_type</td>
						<td>Y</td>
						<td>authorization_code로 고정</td>
					</tr>
					<tr>
						<td>redirect_uri</td>
						<td>Y</td>
						<td>마이 페이지에 등록한 redirect_uri를 넣어야 한다.</td>
					</tr>
					<tr>
						<td>scope</td>
						<td>Y</td>
						<td>read로 고정</td>
					</tr>
					<tr>
						<td>code</td>
						<td>Y</td>
						<td>이전에 받은 authorize_code 입력해야 한다.</td>
					</tr>
				</tbody>
			</table>

			<div className={styles.text2}>
				<h3>요청 예시</h3>
			</div>
			<pre id="json" className={styles.code}>
				curl -v -X POST "https://j9b309.p.ssafy.io/oauth/token" <br />
				-H "Content-Type:application/x-www-form-urlencoded"
				<br />
				-H "Authorzation: Bearer {"${client-id:secret-id을 Base64로 인코딩한 값}"}
				<br />
				-d "grant_type=authorization_code" \ -d "client_id={"${client-id}"}"<br />
				-d "redirect_uri={"${REDIRECT_URI}"}"<br />
				-d "code={"${AUTHORIZE_CODE}"}"
			</pre>

			<div className={styles.text2}>
				<h3>응답 예시</h3>
			</div>

			<pre id="json" className={styles.code}>
				{`
          HTTP/1.1 200 OK Content-Type: application/json;charset=UTF-8
          {
            token_type: "bearer",
            access_token: "Your Access Token",
          }
        `}
			</pre>

			<div className={styles.text}>
				<h2>
					OAuth 사용 예시
					<a
						className={styles.oauthButton}
						href="https://j9b309.p.ssafy.io/excard"
						target="_blank"
						rel="noopener noreferrer"
					>
						사이트 이동
					</a>
				</h2>
			</div>
		</div>
	);
};

export default OauthExplain;
