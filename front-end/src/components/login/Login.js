import React, { useState } from 'react';
import styles from './Login.module.css';
import { useNavigate, Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setUserInfo } from '../../redux/userInfo';
import basicHttp from '../../api/basicHttp';
import tokenHttp from '../../api/basicHttp';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const handleEmail = (e) => {
        setEmail(e.target.value);
    };
    const handlePassword = (e) => {
        setPassword(e.target.value);
    };

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const userInfo = useSelector((state) => {
        return state.persistedReducer.userInfo;
    });

    async function onClickLogin() {
        if (!email) {
            alert('이메일을 입력해주세요');
            return;
        }
        if (!password) {
            alert('비밀번호를 입력해주세요');
            return;
        }

        const userData = {
            email: email,
            password: password,
        };

        try {
            const res = await basicHttp.post(`/docs/service/login`, userData);
            console.log(res);
            console.log('로그인 성공');
            localStorage.setItem('access-token', res.data.data['access-token']);
            localStorage.setItem('refresh-token', res.data.data['refresh-token']);
            dispatch(setUserInfo({ accessToken: res.data.data['access-token'] }));
            console.log(userInfo);
            navigate('/');
            // alert('로그인 성공');
        } catch (error) {
            console.error('로그인 실패:', error);
            alert('로그인 실패');
        }
    }

    return (
        <div className={styles.loginBody}>
            <div className={styles.loginContainer}>
                <div className={styles.logoText}>S.F.O.API</div>
                <input
                    className={styles.loginInput}
                    value={email}
                    onChange={handleEmail}
                    type="text"
                    placeholder="아이디"
                />
                <input
                    className={styles.loginInput}
                    value={password}
                    onChange={handlePassword}
                    type="password"
                    placeholder="비밀번호"
                />
                <button className={styles.loginBtn} onClick={onClickLogin}>
                    로그인
                </button>
                <div className={styles.otherContainer}>
                    <a className={`${styles.rightBorder} ${styles.otherBtn}`} href="#">
                        <span>비밀번호 재설정</span>
                    </a>
                    <Link className={`${styles.alignRight} ${styles.otherBtn}`} to="/signup">
                        <span>회원가입</span>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Login;
