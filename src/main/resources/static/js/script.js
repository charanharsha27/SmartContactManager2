console.log("Inside theme script file");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changeTheme(currentTheme);
  });

function changeTheme(currentTheme){
    // Accessing the css-class property of html element
    document.querySelector("html").classList.add(currentTheme);
    let oldEle = currentTheme;
    
    let themechangeButton = document.querySelector("#light-dark-theme");
    themechangeButton.addEventListener("click", ()=> {
        
        if(currentTheme === 'light'){
            currentTheme = 'dark';
        }
        else{
            currentTheme = 'light';
        }
        
        //changing the theme
        setTheme(currentTheme);


        //removing old property
        document.querySelector("html").classList.remove(oldEle);

        //adding new property
        document.querySelector("html").classList.add(currentTheme);
        console.log(currentTheme);

        let newText = currentTheme === 'light' ? ' Dark' : ' Light';
        themechangeButton.querySelector("span").textContent = newText;
    });
    


}


function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    if(localStorage.getItem("theme")){
        return localStorage.getItem("theme");
    }
    return "white";
}