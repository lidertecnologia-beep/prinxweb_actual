export const showSpinner = (el) => {
  const elClassName = el.current.props.className + 'show-spinner-btn'
  el.current.props.className = elClassName
}

export const hideSpinner = (el) => {
  const elBtnTitle = el.querySelector('.btn .btn_title')
  if (elBtnTitle) {
    elBtnTitle.classList.remove('hide-title_btn')
  }
  const elLoader = el.querySelector('#btn-loader')
  if (elLoader) {
    elLoader.classList.remove('showLoader')
  }
}
