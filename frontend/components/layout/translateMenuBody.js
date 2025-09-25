let openMenu = 0
export const translateMenuBody = (documentBody) => {
  const elBody = documentBody
  if (elBody) {
    const elHeader = elBody.querySelector('header')
    const elMain = elBody.querySelector('main')
    elHeader.classList.add('not-position-header')
    const elMenuHamburguer = elHeader.querySelector('.toogle-menu')
    elMain.classList.add('mt-main-traslate-menu')
    elBody.classList.toggle('translate-menu')
    elBody.classList.add('overflow-hidden')
    // elBody.style.width = "80%";
    openMenu = (openMenu === 0 ? 1 : openMenu - 1)
    if (openMenu === 1 && elMenuHamburguer) elMenuHamburguer.classList.add('icon-close-menu')
    elBody.addEventListener('transitionend', () => {
      (openMenu === 0 ? elBody.classList.remove('overflow-hidden') : '')
      if (elHeader) (openMenu === 0 ? elHeader.classList.remove('not-position-header') : '')
      if (elMain) (openMenu === 0 ? elMain.classList.remove('mt-main-traslate-menu') : '')
      if (elMenuHamburguer) (openMenu === 0 ? elMenuHamburguer.classList.remove('icon-close-menu') : '')
    })
  }
}

export const closeMenuBody = (documentBody) => {
  openMenu = 0
  const elBody = documentBody
  if (elBody) {
    const elHeader = elBody.querySelector('header')
    elBody.classList.toggle('translate-menu', false)
    if (elHeader) {
      const elMenuHamburguer = elHeader.querySelector('.toogle-menu')
      if (elMenuHamburguer) elMenuHamburguer.classList.remove('icon-close-menu')
    }
  }
}
