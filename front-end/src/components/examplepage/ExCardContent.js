import React, { useState, useEffect } from "react";
import { useSearchParams, useLocation } from "react-router-dom";
import styles from "./ExCardContent.module.css";
import basicHttp from "../../api/basicHttp";
import tokenHttp from "../../api/tokenHttp";

const ExCardContent = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const code = searchParams.get("code");

  const [cardList, setCardList] = useState([]);
  const [selectedCardId, setSelectedCardId] = useState(null);
  const [approvedList, setApprovedList] = useState([]);

  useEffect(() => {
    const getCardContent = async () => {
      try {
        const res = await basicHttp.get(`/oauth/access-token?code=${code}`);
        console.log(res);

        const res2 = await basicHttp.get(
          `https://j9b309.p.ssafy.io/api/cards?orgCode=exampleOrgcode&limit=10`,
          {
            headers: {
              Authorization: `Bearer ${res.data.access_token}`,
            },
          }
        );
        setCardList(res2.data.data.cardList);
      } catch (error) {
        console.log(error);
      }
    };
    getCardContent();
  }, []);

  const handleCardClick = async (cardId) => {
    try {
      const res = await basicHttp.get(`/oauth/access-token?code=${code}`);

      const res2 = await tokenHttp.get(
        `https://j9b309.p.ssafy.io/api/cards/transaction?cardId=${cardId}&orgCode=exampleOrgcod&fromDate=2021-01-10&toDate=2024-09-21`,
        {
          headers: {
            Authorization: `Bearer ${res.data.access_token}`,
          },
        }
      );
      setApprovedList(res2.data.data.approvedList);
      setSelectedCardId(cardId);
    } catch (error) {}
  };

  const cardImages = [
    require("../../assets/img/KB1.PNG"),
    require("../../assets/img/KB2.PNG"),
    require("../../assets/img/KB3.PNG"),
    require("../../assets/img/HD1.PNG"),
  ];

  return (
    <div className={styles.chartContainer}>
      <div className={styles.chartCard}>
        <div className={styles.chartTitle}>
          <h1>카드 내역</h1>
        </div>

        {cardList.map((card, index) => (
          <div
            key={index}
            className={`${styles.chartBox} ${styles.chatBox1}`}
            onClick={() => handleCardClick(card.cardId)}
          >
            <div
              className={`${styles.chartNumber} ${styles.chartNumber1}`}
            ></div>
            <div className={`${styles.chartCover} ${styles.chartCover1}`}>
              <img
                src={cardImages[Math.floor(Math.random() * cardImages.length)]}
                alt={card.cardName}
              />
            </div>
            <div className={`${styles.chartName} ${styles.chartName1}`}>
              <span>{card.cardName}</span>
              <div className={styles.priceContainer}>
                <div className={styles.priceTitle}>카드 번호</div>
                <div className={styles.priceContent}>{card.cardNum}</div>
              </div>
              <div className={styles.separator}></div>
            </div>
          </div>
        ))}

        {selectedCardId && (
          <div className={styles.transactionContainer}>
            <h2>선택한 카드의 거래 내역</h2>
            <ul>
              {approvedList.map((transaction, index) => (
                <li key={index}>
                  거래 내역: {transaction}
                  {/* 거래 내역의 다른 정보를 표시하려면 여기에 추가 */}
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </div>
  );
};

export default ExCardContent;
