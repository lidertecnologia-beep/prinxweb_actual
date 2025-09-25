export const translateProfile = (el) => {
  if (el) {
    const elProfile = el.current.querySelector('.profile')
    if (elProfile) elProfile.classList.toggle('toogle-profile')
  }
}
