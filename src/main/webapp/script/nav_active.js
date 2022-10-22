if (window.location.pathname === "/" || window.location.pathname === "" || window.location.pathname === "/cinema/") {
    let element = document.querySelector('.home');
    element.classList.add('active');
    element.setAttribute('aria-current', 'page');
} else {
    document.querySelectorAll('.nav-link').forEach(
        link => {
            if (link.href === window.location.href) {
                link.classList.add('active');
                link.setAttribute('aria-current', 'page')
            }
        }
    )
}
