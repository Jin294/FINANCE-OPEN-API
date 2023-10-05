// src/components/mypage/Mypage.js

import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Mypage.module.css';

import basicHttp from '../../api/basicHttp';
import tokenHttp from '../../api/tokenHttp';
import copyImg from '../../assets/img/copy.png'

const Mypage = () => {
    const [apiToken, setApiToken] = useState('');
    const [redirectURI, setRedirectURI] = useState('');
    const [clientList, setClientList] = useState([]);
    const handleapiToken = (e) => {
        setApiToken(e.target.value);
    };
    const handleRedirectURI = (e) => {
        setRedirectURI(e.target.value);
    };

    const getClientList = async () => {
        try {
            const res = await tokenHttp.get(`/docs/service/client`);
            console.log(res);
            setClientList(res.data.data);
        } catch (error) {
            if (error.message === 'no token' || error.message === 'expire token') {
                navigate('/login'); // 토큰없음이나 토큰만료 에러발생시 로그인화면으로 이동
            }
        }
    };
    const onClickAddRedirectURI = async () => {
        if (!redirectURI) {
            // 내용이 없을 경우 처리
            return;
        }
        try {
            const res = await tokenHttp.post(`/docs/service/client`, {
                web_server_redirect_uri: redirectURI,
            });
            console.log(res.data.data);
            setClientList((clientList) => [
                ...clientList,
                {
                    web_server_redirect_uri: res.data.data.webServerRedirectUri,
                    client_id: res.data.data.clientId,
                    client_secret: res.data.data.clientSecret,
                },
            ]);
            // window.location.reload(); // 페이지 새로고침
        } catch (error) {
            if (error.message === 'no token' || error.message === 'expire token') {
                navigate('/login'); // 토큰없음이나 토큰만료 에러발생시 로그인화면으로 이동
            }
        }
    };

    const navigate = useNavigate();

    const handleCopyClipBoardToken = async (text) => {
        try {
          await navigator.clipboard.writeText(text.apiToken);
        } catch (error) {
        }
      };

    const handleCopyClipBoard = async (text) => {
        try {
          await navigator.clipboard.writeText(text);
        } catch (error) {
        }
      };

    useEffect(() => {
        const getApiToken = async () => {
            try {
                const res = await tokenHttp.get(`/docs/service/token`);
                console.log(res);
                console.log('api토큰 발급 성공');

                localStorage.setItem('api-token', res.data.data);
                setApiToken(res.data.data);
            } catch (error) {
                if (error.message === 'no token' || error.message === 'expire token') {
                    navigate('/login'); // 토큰없음이나 토큰만료 에러발생시 로그인화면으로 이동
                }
            }
        };
        getApiToken();
        getClientList();
    }, []);
    // input value 수정되면 set해주는 함수

    async function onClickapiTokenRefreshBtn() {
        try {
            const res = await tokenHttp.post(`/docs/service/token`);
            console.log(res);
            console.log('api토큰 재발급 성공');

            localStorage.setItem('api-token', res.data.data);
            setApiToken(res.data.data);
            alert('api토큰 재발급 성공');
        } catch (error) {
            if (error.message === 'no token' || error.message === 'expire token') {
                navigate('/login'); // 토큰없음이나 토큰만료 에러발생시 로그인화면으로 이동
            }
            console.error('api토큰 재발급  실패:', error);
            alert('api토큰 재발급  실패');
        }
    }

    return (
        <div className={styles.mypageBody}>
            <div className={styles.mypageContainer}>
                <div className={styles.logoText}>마이페이지</div>
                <div className={styles.textBox}>
                    {apiToken}
                    <img src={copyImg} onClick={() => handleCopyClipBoardToken({apiToken})}></img></div>
                <button className={styles.apiTokenRefreshBtn} onClick={onClickapiTokenRefreshBtn}>
                    토큰 재발급
                </button>
                <input
                    className={styles.inputBox}
                    value={redirectURI}
                    onChange={handleRedirectURI}
                    type="text"
                    placeholder=""
                />
                <button className={styles.apiTokenRefreshBtn} onClick={onClickAddRedirectURI}>
                    RedirectURI 추가하기
                </button>
                {/*redirectURI 목록*/}
                {clientList.map((client) => (
                    <div key={client.client_id} className={styles.client}>
                        <label className={styles.clientText}>RedirectURI</label>
                        <div className={styles.textBox}>{client.web_server_redirect_uri}
                            <img src={copyImg} onClick={() => handleCopyClipBoard(client.web_server_redirect_uri)}></img>
                        </div>
                        {/* <input
                            className={styles.clientContent}
                            value={client.web_server_redirect_uri}
                            type="text"
                            disabled
                        /> */}
                        <label className={styles.clientText}>client-id</label>
                        <div className={styles.textBox}>{client.client_id}
                            <img src={copyImg} onClick={() => handleCopyClipBoard(client.client_id)}></img>
                        </div>
                        {/* <input className={styles.clientContent} value={client.client_id} type="text" disabled /> */}
                        <label className={styles.clientText}>secret-id</label>
                        <div className={styles.textBox}>{client.client_secret}
                            <img src={copyImg} onClick={() => handleCopyClipBoard(client.client_secret)}></img>
                        </div>
                        {/* <input className={styles.clientContent} value={client.client_secret} type="text" disabled /> */}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Mypage;
