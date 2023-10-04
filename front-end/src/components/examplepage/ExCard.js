import React, { useState, useEffect } from 'react';
import styles from './ExCard.module.css';
import China from '../../assets/img/China.PNG';
import JPN from '../../assets/img/JPN.PNG';
import US from '../../assets/img/US.PNG';
import EU from '../../assets/img/EU.PNG';

import basicHttp from '../../api/basicHttp';
import tokenHttp from '../../api/tokenHttp';

const ExCard = () => {
    const [exchangeAllList, setExchangeAllList] = useState([]);

    useEffect(() => {
        const getExchangeAll = async () => {
            try {
                const res = await basicHttp.get(`/api/exchange`);
                console.log(res.data.data.list);
                console.log(res.data.data.list[2].price);

                setExchangeAllList(res.data.data.list);
            } catch (error) {}
        };
        getExchangeAll();
    }, []);

    return (
        <div className={styles.chartContainer}>
            <div className={styles.chartCard}>
                <div className={styles.chartTitle}>
                    <h1>카드 불러오기</h1>

                    {/* <!-- Button --> */}
                    <div className={styles.link}>
                        <a
                            href="https://j9b309.p.ssafy.io
/oauth/authorize?response_type=code&client_id=test&redirect_uri=https://j9b309.p.ssafy.io/oauth/test&scope=read"
                        >
                            내 카드내역 불러오기
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ExCard;