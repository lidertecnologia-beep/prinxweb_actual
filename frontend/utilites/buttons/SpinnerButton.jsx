const SpinnerButton = ({ showSpinner }) => {
  return (
    <>
      <div id='btn-spinner' className={showSpinner ? 'spinner-btn show-spinner-btn' : 'spinner-btn'} />
    </>
  )
}

export default SpinnerButton
