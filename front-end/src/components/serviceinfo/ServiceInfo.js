// src/components/ServiceInfo.js
import React, { useEffect, useState } from "react";
import styles from "./ServiceInfo.module.css";

import basicHttp from "../../api/basicHttp";
import { NavLink } from "react-router-dom";

const ServiceInfo = () => {
	const [title, setTitle] = useState([]);
	const [detail, setDetail] = useState([]);
	const [tabUrls, setTabUrls] = useState([]);
	const [exUrls, setExUrls] = useState([]);

	useEffect(() => {
		getAllServiceData();
		getTabUrls();
		getExUrls();
	}, []);

	const getAllServiceData = async () => {
		try {
			const response = await basicHttp.get("/docs/api/category");
			const responseData = response.data;
			console.log("responseData", responseData.data);

			if (responseData.data) {
				setTitle(responseData.data.map((item) => item.title));
				setDetail(responseData.data.map((item) => item.detail));
			}
		} catch (error) {
			console.log("Error fetching service data", error);
		}
	};

	const getTabUrls = () => {
		const urls = [
			"/apidock/financialdata",
			"/apidock/exchange",
			"/apidock/investment",
			"/apidock/consumption",
		];
		setTabUrls(urls);
	};
	const getExUrls = () => {
		const urls = ["/excard", "/exexchange", "/excard", "/excard"];
		setExUrls(urls);
	};

	return (
		<div className={styles.backBody}>
			<div className={styles.boardContainer}>
				<div className={styles.boardTop}>
					<h3>제공하는 API 서비스를 소개합니다</h3>
				</div>
				<div className={styles.cardList}>
					{title.map((item, index) => (
						<div className={styles.card} key={index}>
							<h3>{item}</h3>

							<div>
								<p>{detail[index]}</p>
							</div>
							<div className={styles.buttonContainer}>
								<NavLink to={tabUrls[index]} className={styles.button}>
									<p>문서보기</p>
								</NavLink>
								<NavLink to={exUrls[index]} className={styles.button}>
									<p>사용예시</p>
								</NavLink>
							</div>
						</div>
					))}
				</div>
			</div>
		</div>
	);
};

export default ServiceInfo;
