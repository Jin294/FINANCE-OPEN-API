const emailButton = document.getElementById("email-button");
        const emailWrap = document.getElementById("email-wrap");
        const passwordWrap = document.getElementById("password-wrap");
        const passwordbutton = document.getElementById("password-button");
        const emailBack = document.getElementById("email-back");
        const email = document.getElementById("email")
        const password = document.getElementById("password")

        emailButton.addEventListener("click", () => {
            console.log(email.value)
            if(email.value.trim() == "") {
                alert("이메일을 입력해주세요")
                return
            }
            emailButton.hidden = !emailButton.hidden
            emailWrap.hidden = !emailWrap.hidden
            passwordWrap.hidden = !passwordWrap.hidden
            passwordbutton.hidden = !passwordbutton.hidden
            emailBack.hidden = !emailBack.hidden

            axios.get(`http://j9b309.p.ssafy.io/oauth/user/email/${email.value}`).then((res) => {
                console.log(res)
            })
        })

        emailBack.addEventListener("click", () => {
            emailButton.hidden = !emailButton.hidden
            emailWrap.hidden = !emailWrap.hidden
            passwordWrap.hidden = !passwordWrap.hidden
            passwordbutton.hidden = !passwordbutton.hidden
            emailBack.hidden = !emailBack.hidden
        })
